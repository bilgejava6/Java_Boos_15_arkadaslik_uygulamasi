package com.muhammet.arkadaslik_uygulamasi.controller;

import com.muhammet.arkadaslik_uygulamasi.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;
}
