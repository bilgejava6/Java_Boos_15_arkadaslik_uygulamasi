package com.muhammet.arkadaslik_uygulamasi.repository;

import com.muhammet.arkadaslik_uygulamasi.entity.Gender;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import com.muhammet.arkadaslik_uygulamasi.views.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA, spring boot un yapısı gereği, her bir interface için gerekli olan
 * IMPL sınıflarını generate ederek onların nesne referanslarını(yani bean lerini)
 * application context i nin içerisine atar. Gerekli olduğunda buradan kullanırsınız.
 */
public interface UserRepository extends JpaRepository<User,Long> {
   // List<User> gelSenButunDatalariBanaVer();
    /**
     * Kadın kullanıcıların listesi
     */
    // YANLIŞ: List<User> kadinKullanicilariGetir();
    /**
     * JPA REpository belli keyword ler ile sorgu methodları oluşturmanıza izin verir.
     * -- JPA kendine ait ethodların gövdelerini yazabilir
     * -- JPA kendi kelimeleri ile oluşturulan methodların da gövdelerini yazabilir,ancak
     * kurallara uygun yazılır ise.
     * ÖRN: cinsiyete göre kullanıcı listesi
     * select * from tblUser where gender = ?1
     * 1- find (sorgu türünü belirtiyoruz.) - All(opsiyonel dir.)
     * 2- by - where komutuna denk gelir.
     * 3- Entity içerisinde tanımlanmış değişken adını yazıyorsunuz Ancak değişkenin ilk harfi
     * büyük harf ile başlamalı.
     * 4- eğer sorgu bir değer talep ediyor ise talep edilen değişken türünde bir değer değişkeni
     * method parametresi olarak tanımlanır.
     */
    List<User> findAllByGender(Gender bu_degisken_kafana_gore_yazilabilir);

    /**
     * birden fazla alan için sorgu yapmak istersek,
     * select * from tbluser where gender = ?1 and age= ?2
     * select * from tbluser where gender = ?1 or name= ?2
     * OR - AND
     */
    List<User> findAllByGenderAndAge(Gender gender, int age);
    List<User> findAllByGenderOrName(Gender gender,String name);

    /**
     * sorgu sadece tek bir sonuç dönecek ise return type
     * Entity ya da Optional<Entity> şeklinde kullanılır.
     * select * from tbluser where userName= ?1
     */
    // bu sorgu kullanıcıyı bulursa değer döner yok ise null döner
    User findByUserName(String userName);
    Optional<User> findOptionalByUserName(String userName);

    /**
     * belli bir yaşın üzerindeki kullanıcıları bulmak
     * select * from tbluser where age>?1
     * select * from tbluser where age>=?1
     */
    List<User> findAllByAge(int age); // bu yaşı buna eşit olanları liste
    List<User> findAllByAgeGreaterThan(int age); // yaşı bundan büyük olanlar
    List<User> findAllByAgeGreaterThanEqual(int age);// yaşı buna eşit ve büyük olanlar
    List<User> findAllByAgeLessThan(int age); // küçük olanlar
    List<User> findAllByAgeLessThanEqual(int age);// eşit ve küçük olanlar

    /**
     * kullanıcı adlarında belli bir kelimeye göre içerikte arama yapmak
     * select * from tbluser where userName like '%[VALUE]%' içeriyorsa
     * select * from tbluser where userName like '[VALUE]%' bununla başlıyorsa
     * select * from tbluser where userName like '%[VALUE]' bununla bitiyorsa
     * select * from tbluser where userName like '_a_k[VALUE]%' 3 harf ile başlayıp sonra boşluk sonra aranan kelimeyi içeriyorsa
     * like bire bir eşleşme arar -> muhammet == muhammet olmalı | like ile Muhammet == muhammet olmaz
     * ilike
     * findAllByUserNameLike("__m%");
     */

    List<User> findAllByUserNameLike(String userName);
    List<User> findAllByUserNameLikeIgnoreCase(String userName);

    /**
     * bununla başlayanlar, -> startWith
     * bununla bitenler -> endWith
     * bunu içerenler -> containing
     *
     */
    List<User> findAllByUserNameStartingWith(String userName);
    List<User> findAllByUserNameEndingWith(String userName);
    List<User> findAllByUserNameContaining(String userName);

    List<User> findAllByUserNameContainingIgnoreCaseAndAgeGreaterThanAndEmailEndingWith(String userName,int age, String email);

    /**
     * sorgu neticesinde dönen verilerde sıuralama yapmak.
     * select * from tbluser orderby age [DESC,ASC]
     *
     * select * from tbluser where name = ?1 orderby age desc
     */
    List<User> findAllByOrderByAge(); // A..Z, 0..9
    List<User> findAllByOrderByAgeDesc();// z..a, 9..0
    List<User> findAllByNameOrderByAgeDesc(String name);

    /**
     * sorgular genellikle kısıtlanarak kullanılır, böylece sogu performansı arttırılmış olurr
     * select * from tbluser limit 5 [TOP]
     * yaşı en büyük olan kullanıcı kim?
     * select * from tbluser orderby age desc limit 1
     * -> find, findAll, findTop
     * yaşı en küçük ilk 5 kullanıcı
     */
    User findTopByOrderByAgeDesc();
    List<User> findTop5ByOrderByAge();

    /**
     * belli aralıkları sorgulamak,
     * select * from tbluser where age>5 and age<25 -> (5,25)
     * select * from tbluser where age>=5 and age=<25 -> [5,25]
     */
    List<User> findAllByAgeBetween(int start, int end); // [start,end]
    List<User> findAllByUserNameContainingAndAgeBetween(String userName,int startAge,int endAge);


    /**
     * boolean değeri sorgularken kullanılacak keyword
     * hesabı aktif olan kullanıcılar
     */
    List<User> findAllByIsActive(boolean isActive);
    List<User> findAllByIsActiveTrue(); // aktif olanlar
    List<User> findAllByIsActiveFalse();// pasif olanlar

    /**
     * mükerrer kayıtları tekilleştirmek
     * uygulamamızda kullanılan faklı isimlerin listesi
     */
    //List<User> findDistinctBy();
    // List<String> findDistinctByNames();

    List<User> findAllByIsActiveIsNull(); // aktif bilgisi NULL olanlar
    List<User> findAllByIsActiveIsNotNull(); // aktid bilgisi NULL olmayanlar

    /**
     * Belli kullanıcıların listesine ihtiyaç duymaktayız, burada id kullanılacak diyelim.
     * id si, 5-9-98-541-24-5889-44-85
     * bir kişinin takipçilerinin id ler elimizde olsun, bu kullanıcıların bilgilerine ihtiyacımız var
     * select * from tbluser where id in(5,9,98,....)
     * select * from tbluser where id  in(select followerid from tblfollow where userid=5)
     */
    List<User> findAllByIdIn(List<Long> ids);

    /**
     * JPQL -> Jakarta Persistence Query Language
     * HQL -> Hibernate Query Language
     * NATIVESQL -> bildiğiniz SQL sorguları
     *
     * JPQL -> select u from User u
     * HQL -> from User
     * NATIVESQL -> select * from User
     *
     * Aşağıdaki method içi Spring gövde oluştururken, Query içerisine
     * yazılan sorguyu kullanacak ve method a geçilen değeler sorguya işlenerek
     * sogunun sonucu methodun return type ı ile dönecektir.
     */
    @Query("select u from User u where u.name= ?1")
    List<User> banaAdiSuOlanKullanicilariGetir(String ad);

    @Query("select u from User u where u.age=?1 and u.email like ?2")
    List<User> bulYasiSuOlanVeMailAdresiBuOlan(int yas, String mailAdres);

    @Query(nativeQuery = true,value = "select * from User where age>?1")
    List<User> yasiBuyukOlanlariGetir(int yas);

    /**
     * mesela login olacak bir kullanıcı Un,Pwd girmeli bu kritere uyan bir kayıt
     * var ise giriş yapmalı.
     *
     * select COUNT(*)>0 from tbluser userName=? and password=?
     *
     */
    @Query("select COUNT(u)>0 from User u where u.userName=?1 and u.password=?2")
    Boolean buKullaniciVarMi(String userName, String password);

    Boolean existsByUserNameAndPassword(String userName, String password);

    /**
     *
     * select return elamanları ile result type uyumlu olmalı
     */
    @Query("select new com.muhammet.arkadaslik_uygulamasi.views.VwUser(u.userName,u.name,u.avatar) from User u")
    List<VwUser> tumKullanicilariGetir();

}
/**
 * JpaRepository -> içerisindeki tüm methodların kodlamaları standarttır. Bu
 * nedenle Spring ilgili sınıfların mesela UserRepository için
 * UserRepositoryImpl sınıfını oluşturabilir.
 * ancak, burada tanımı yapılan method  nasıl oluşturulacağı hakkında bir bilgi
 * içermediği için nesne yaratma sürecini baltaladı.
 */