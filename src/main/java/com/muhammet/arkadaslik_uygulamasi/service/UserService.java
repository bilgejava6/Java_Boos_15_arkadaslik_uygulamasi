package com.muhammet.arkadaslik_uygulamasi.service;

import com.muhammet.arkadaslik_uygulamasi.entity.Gender;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import com.muhammet.arkadaslik_uygulamasi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
