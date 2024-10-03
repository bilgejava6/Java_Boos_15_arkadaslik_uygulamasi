package com.muhammet.arkadaslik_uygulamasi.repository;

import com.muhammet.arkadaslik_uygulamasi.entity.Gender;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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



}
/**
 * JpaRepository -> içerisindeki tüm methodların kodlamaları standarttır. Bu
 * nedenle Spring ilgili sınıfların mesela UserRepository için
 * UserRepositoryImpl sınıfını oluşturabilir.
 * ancak, burada tanımı yapılan method  nasıl oluşturulacağı hakkında bir bilgi
 * içermediği için nesne yaratma sürecini baltaladı.
 */