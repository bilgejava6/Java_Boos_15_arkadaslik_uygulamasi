package com.muhammet.arkadaslik_uygulamasi.controller;

import com.muhammet.arkadaslik_uygulamasi.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * DİKKAT!!!!!
 * ContructorInjection işlemi, sınıf için gerekli conrtructor u ve talep edilen değişkeni
 * yazarak tanımlayabiliriz. Bakınız. UserController
 * ancak bu işlemi kolayca Lombok kullanarakta yapabiliriz. Bunun için lombok un @RequiredArgConstructor
 * anotasyonu kullanılır.
 */
@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
}
