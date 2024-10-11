package com.muhammet.arkadaslik_uygulamasi.controller;

import com.muhammet.arkadaslik_uygulamasi.dto.request.AddFollowRequestDto;
import com.muhammet.arkadaslik_uygulamasi.dto.response.BaseResponse;
import com.muhammet.arkadaslik_uygulamasi.service.FollowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.muhammet.arkadaslik_uygulamasi.constants.RestApis.*;

@RestController
@RequestMapping(FOLLOW)
@RequiredArgsConstructor
public class FollowController {
    private final FollowService followService;

    /**
     *
     * HTTP Request Types
     * GET
     * bir sunucuya bilgi almak amaçlı gönderilen istek türüdür.
     * parametre gönderebilirsiniz, ancak güvenli değildir. Genellikle
     * herkese açık ve güvenliği sorun olmadığı işlemlerde kullanılır.
     *
     * POST
     * yeni bir veri yaratmak amacıyla bilgi gönderimide yapılan istek
     * türüdür. Bilgiler isteğin gövdesinde gönderilir ve veriler güvenli
     * bir şekilde iletilir
     */

    @PostMapping(ADDFOLLOW)
    public ResponseEntity<BaseResponse<Boolean>> addFollow(@RequestBody @Valid AddFollowRequestDto dto){
        followService.addFollow(dto);
        return ResponseEntity.ok(
                BaseResponse.<Boolean>builder()
                        .data(true)
                        .success(true)
                        .message("takip işlemi başarı ile tamamlandı")
                        .code(200)
                        .build()
        );
    }



}
