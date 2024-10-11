package com.muhammet.arkadaslik_uygulamasi.controller;

import static com.muhammet.arkadaslik_uygulamasi.constants.RestApis.*;

import com.muhammet.arkadaslik_uygulamasi.dto.request.RegisterRequestDto;
import com.muhammet.arkadaslik_uygulamasi.dto.response.BaseResponse;
import com.muhammet.arkadaslik_uygulamasi.entity.Gender;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import com.muhammet.arkadaslik_uygulamasi.service.UserService;
import com.muhammet.arkadaslik_uygulamasi.views.VwUser;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(USER)
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * http://localhost:9090/user/add-user
     */
    @GetMapping(ADDUSER)
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
    @GetMapping(GETALLUSERS)
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    /**
     *
     * http://localhost:9090/user/get-all-user-f
     */
    @GetMapping(GETALLUSERSF)
    public List<User> getAllUsersFemale(){
        return userService.kadinKullaniciListesi();
    }

    @PostMapping(LOGIN)
    public void doLogin(String userName,String password){
        System.out.println("Kullanıcı adı...: "+ userName);
        System.out.println("Şifre...........: " + password);
    }

    /**
     * create-user adında bir method oluşturun
     * username, password, email, name
     * method kullanıcı oluşturma işlevini yapacak.
     */
    @PostMapping(CREATEUSER)
    public void createUser(String userName, String password, String email, String name){
        userService.kullaniciKayitEt(userName,password,email,name);
    }

    /**
     * 1- kullanıcı adını(bir kısmını da olabili örn: ahm) alarak liste dönen bir method yazınız (GET)
     * 2- id si verilen kullanıcıyı dönen method.
     */
    @GetMapping(FINDALLBYUSERNAME)
    public List<User> findAllByUserName(String userName){
      return  userService.findAllByUserName(userName);
    }

    @GetMapping(FINDBYID)
    public User findById(Long id){
       return userService.findById(id);
    }

    /**
     * Data Transfer Object - DTO
     * Bilgi alırken ya da gönderirken -> Request-Response
     * Bilgileri alırken kısıtlı tutmak ve ihtiyaca göre talep etmek zorundayız.
     * Gelen verileni tutarlılığını Controller katmanında incelemek zorundayız.
     * Kullanıcıya dönüş yapacağımız bilgileri kısıtlamak zorundayız zira,
     * güvenlik için gizlenmesi gereken bilgiler vardır. Ayrıca ne kadar
     * çok veri gönderimi okadar kaynak kullanımı demektir.
     */
    @PostMapping(REGISTER)
    public ResponseEntity<User> register(@RequestBody @Valid RegisterRequestDto dto){
      if(!dto.getPassword().equals(dto.getRePassword()))
          return ResponseEntity.badRequest().body(null);
      User user =  userService.register(dto);

      return ResponseEntity.ok(user);
    }


    @GetMapping("/get-all-user-list")
    public  ResponseEntity<List<VwUser>> getAllUserList(){
        return ResponseEntity.ok(userService.getAllUserList());
    }


    @GetMapping("/get-all-view-users")
    public ResponseEntity<BaseResponse<List<VwUser>>> getAllViewUser(){
        return ResponseEntity.ok(
                BaseResponse.<List<VwUser>>builder()
                        .success(true)
                        .code(200)
                        .message("Kullanıcı listesi başarılı şekilde getirildi.")
                        .data(userService.getAllUserList())
                        .build()
        );
    }




}
