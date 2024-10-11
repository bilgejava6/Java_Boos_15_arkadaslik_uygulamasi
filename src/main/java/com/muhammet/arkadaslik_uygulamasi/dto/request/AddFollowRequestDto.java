package com.muhammet.arkadaslik_uygulamasi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddFollowRequestDto {
    /**
     * String userName;
     * kullanıcı tarafından bu değerin hiç gönderilmemesi
     * body{
     *     // burada hiç bir keyword yok ise
     * }
     * NotNull -> userName = null
     * body{
     *     userName = ""
     * }
     * NotEmpty -> userName = ""
     * *** NOT NotNull ile NotEmpty ayrı ayrı
     * çalıştığı konusunu konuşalım.
     */
    @NotNull // userId = null
    // @NotEmpty // userId =
    Long userId;
    @NotNull
    //@NotEmpty
    Long followingId;
}
