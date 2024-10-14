package com.muhammet.arkadaslik_uygulamasi.service;

import com.muhammet.arkadaslik_uygulamasi.dto.request.RegisterRequestDto;
import com.muhammet.arkadaslik_uygulamasi.dto.request.UpdateUserProfileRequestDto;
import com.muhammet.arkadaslik_uygulamasi.entity.Gender;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import com.muhammet.arkadaslik_uygulamasi.mapper.UserMapper;
import com.muhammet.arkadaslik_uygulamasi.repository.UserRepository;
import com.muhammet.arkadaslik_uygulamasi.views.VwUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * DİKKAT!!!!! spring boot bir sınıfın referanını oluşturmak için mutlaka
 * o sınıfın işaretli olmasını talep eder, yani bir sınıftan nesne türetilecek mi
 * bilmek ister. Bunu yapabilmek için anotasyon kullanırız. Böylece spring
 * componentScan sistemi ile işaretli sınıfları Bean olarak işaretler ve
 * çalışma zamanında bu sınflardan birer bean yaratır.
 * servis sınıfları için @Service anoytayonu kullanılır.
 */
@Service
public class UserService {
    /**
     * Servis temel işlevleri yerine getirir. Gerekli gördüğü bilgileri repository
     * e aktarır. Bu nedenle servis sınıfında repository nesnelerine ihtiyaç duyulur.
     * Burada repository değişkeni tanımlanmalı ve nesnesi oluşturulmalıdır.
     */
    //    @Autowired
    //    private UserRepository userRepository;

    /**
     * Dependency Injection  / DI
     * Spring boot ta bağımlılıkları sağlamak injekte etmek için farklı yöntemler
     * kullanılır. en bilindikleri,
     * 1- @Autowired anotasyonu ile kullanım
     * bağlılık atfedilen değişkennin üzerine eklenir ise, spring boot ApplicatonContext
     * içerisinde ilgili sınıftan oluşturulmuş olan nesne referansını ilgili değişkene atar.
     * 2- Constructor Injection ile kullanım
     * Spring boot bir sınıfın oluşturulabilmesi için gerkli olan Constructor methodlarını
     * incelerken eğer method nesne talep ediyor ise, ApplicationContext içindeki
     * nesnelerden bunu sağlamaya çalışır ve nesne referanslarını bu contructor a
     * parametre olarak geçer.
     */
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(String userName, String avatar, Gender gender){
        User user = User.builder().avatar(avatar).gender(gender).name(userName).build();
        userRepository.save(user);
    }

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public List<User> kadinKullaniciListesi(){
        return userRepository.findAllByGender(Gender.FEMALE);
    }

    public void kullaniciKayitEt(String userName, String password, String email, String name) {
        userRepository.save(User.builder()
                        .userName(userName)
                        .password(password)
                        .email(email)
                        .name(name)
                .build());
    }

    public List<User> findAllByUserName(String userName) {
       return userRepository.findAllByUserNameContaining(userName);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public User register(RegisterRequestDto dto) {
      return  userRepository.save(User.builder()
                        .userName(dto.getUserName())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                .build());
    }

    public List<VwUser> getAllUserList() {
        return  userRepository.tumKullanicilariGetir();
    }

    /**
     * Aranılan kullanıcı id sini DB de sorgular, kayıt var ise true, yok ise false döner.
     *
     * @param userId
     * @return
     */
    public boolean existsById(Long userId) {
       // return userRepository.findById(userId).isPresent();
        return userRepository.existsById(userId);
    }

    /**
     * Aranılan kullanıcı idsi ile optional olarak kullanıcıyı döner.
     * @param userId
     * @return
     */
    public Optional<User> findOptionalById(Long userId){
        return userRepository.findById(userId);
    }

    public List<User> findAllByIdIn(List<Long> followingIds) {
        return userRepository.findAllByIdIn(followingIds);
    }

    public void updateUserProfile(UpdateUserProfileRequestDto dto) {
       // User user = User.builder().name("murat").build(); // yeni kayıt
       // User user1 = User.builder().id(3L).name("murat").build(); // eğer 3 id var ise üzerine yazar,
       // User user2 = User.builder().id(3L).name("deniz").build(); // önceki kaydın üzerine yazar.
        /**
         * Burada dto -> user a dönüşüm yaptık ve elle oluşturduk.
         * peki 50 adet alan olaydık 50 alan için böye dönüşüm mü yapacaktık.
         * ve ayrıca zaman içerisinde alanların adları değişse idi bu değişklik nasıl yapılacaktı?
         */
//        User user = User.builder()
//                .id(dto.getId())
//                .name(dto.getName())
//                .userName(dto.getUserName())
//                .password(dto.getPassword())
//                .avatar(dto.getAvatar())
//                .email(dto.getEmail())
//                .phone(dto.getPhone())
//                .age(dto.getAge())
//                .build();
//        userRepository.save(user);
//
        User user = UserMapper.INSTANCE.eyyyyDtoUserNesnesineDonus(dto);
        userRepository.save(user);

    }
}
