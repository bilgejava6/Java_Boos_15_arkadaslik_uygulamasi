package com.muhammet.arkadaslik_uygulamasi.controller;

import com.muhammet.arkadaslik_uygulamasi.entity.Gender;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import com.muhammet.arkadaslik_uygulamasi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Control sınıfları son kullanıcı ile iletişime geçiş için gelen isteleri handle edecek
 * sınıflardır. Burada web için gerekli erişimler tanımlanır ve istekler işlenir.
 * @Controller -> web MCV için kullanılır.
 * @RestController -> restAPI kullanımları için
 * bir diğer önemli nokta ise, bu sınıf bir web iletişim sınıfı olduğu için internet ya da intranet
 * üzerinden gelen isteklerin yakalanabilmesi için PATH tanımı yapılması gereklidir. İligli sınıf için
 * tanımlama
 * @RequestMapping("/[PATH_NAME]") şeklinde yapılır.
 * bu işlem şunu ifade eder,
 * http://[IP Adres ya da DomainName]:[PORT]/[PATH_NAME]
 * userController için erişim
 * http://localhost:9090/user
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * http://localhost:9090/user/add-user
     */
    @GetMapping("/add-user")
    public String addUser(){
        userService.addUser("muhammethoca","http://picsum.photos/100/100", Gender.MALE);
        userService.addUser("deniz","http://picsum.photos/100/100", Gender.MALE);
        userService.addUser("bahar","http://picsum.photos/100/100", Gender.FEMALE);
        userService.addUser("canan","http://picsum.photos/100/100", Gender.FEMALE);
        return "kayıtlar başarı ile eklendi.";
    }

    /**
     * http://localhost:9090/user/get-all-users
     */
    @GetMapping("/get-all-users")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    /**
     *
     * http://localhost:9090/user/get-all-user-f
     */
    @GetMapping("/get-all-user-f")
    public List<User> getAllUsersFemale(){
        return userService.kadinKullaniciListesi();
    }
}
