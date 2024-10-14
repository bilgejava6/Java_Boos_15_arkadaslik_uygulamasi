package com.muhammet.arkadaslik_uygulamasi.service;

import com.muhammet.arkadaslik_uygulamasi.dto.request.AddFollowRequestDto;
import com.muhammet.arkadaslik_uygulamasi.entity.Follow;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import com.muhammet.arkadaslik_uygulamasi.exception.ArkadaslikException;
import com.muhammet.arkadaslik_uygulamasi.exception.ErrorType;
import com.muhammet.arkadaslik_uygulamasi.repository.FollowRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowService {

    private final FollowRepository repository;
    private final UserService userService;
    private final FollowRepository followRepository;

    public FollowService(FollowRepository repository, UserService userService, FollowRepository followRepository){
        this.repository = repository;
        this.userService = userService;
        this.followRepository = followRepository;
    }


    public void addFollow(AddFollowRequestDto dto) {
        /**
         * şuan -> id: 30 olan kullanıcımız var.
         * userId;= 3000
         * following= 5645646;
         *
         * true -> doğru
         * !true -> yanlış
         */
       if(!userService.existsById(dto.getUserId()) || !userService.existsById(dto.getFollowingId()))
           throw new ArkadaslikException(ErrorType.FOLLOW_USERID_FOLLOWINGID_NOTFOUND);

        repository.save(Follow.builder()
                        .userId(dto.getUserId())
                        .followId(dto.getFollowingId())
                .build());
    }

    /**
     * ->Bir kullanıcının takipte olduğu kullanıcıların listesi.
     * follow tablosundan bulunur.
     * "select * from tblfollow where userId = ?"
     * -> bulduğunuz follow listesin içerisindeki takip edilen
     * kişilerin id lerini alıp user tablosuna sormak
     * -> user tablosundan bulunan kullanıcıları geri dönmek üzerer
     * bir listesinin içerisine koymak.
     * @param userId
     * @return
     */
    public List<User> getMyFollowing(Long userId) {
        /**
         * Bu kullanımda DB den follow tablosuna ait tüm kolonları ihtiva eden dataları çekeriz
         * bu nedenle ihtiyacımız olmayan %66 lık bir veriyi gereksiz çekmek durumunda kalırız.
         */
        // List<Follow> takipciler = followRepository.findAllByUserId(userId);
        /**
         * bu kullanım daha efektif ve sadece ihtiyacımız olan dataları getirir.
         */
        List<Long> followingIds = followRepository.findAllFollowIdByUserId(userId);
        /**
         * Buradaki kullanımda 2 noktada sorunludur.
         * 1. kısım ihtiyacımız olmadığı halde çektiğimiz follow bilgisinin içinde sadece Id bilgisinii
         * almak için tüm datayı for ile dönerek gereksiz bir işlem gücü sarfederiz.
         * 2. her bir kayıt için tekrar tekrar DB sorgusu atıyoruz. yani userservis üzerinde her döngüde
         * kullanıcı sorguluyoruz.
         * ÖRNEK HESAP:
         * 10.000 kullanıcı
         * Long -> 8byte
         * -> follow list[id,userId,followId] -> 24byte * 10.000 -> 240kb
         * -> long list -> 80kb
         * -----------
         * Zaman maliyeti:
         * 10.000 kayıt için for döngüsü 1sn ye işlem yapsın. ancak her bir döngüde kullanıcı bilgisi
         * sorgulanmak zorunda. Bu nedenle her döngü DB ye istek atıp bilgi çekmeli.
         * 1 sorgu işlemi 1ms sürsün
         * 10k -> 10sn
         */
//        List<User> takipciListesi = new ArrayList<>();
//        takipciler.forEach(t->{
//            System.out.println(t.getFollowId()); // takip ettiklerinin id si
//            User user = userService.findById(t.getFollowId());
//            takipciListesi.add(user);
//        });

        /**
         * Çözüm:
         * tüm kullanıcı id lerini tek seferde DB de sorgulatmak doğru olacaktır.
         * select * from tbluser where id in(54,54,35345,435,35,)
         */
        List<User> followingUserList = userService.findAllByIdIn(followingIds);
        return followingUserList;
    }

}
