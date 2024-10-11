package com.muhammet.arkadaslik_uygulamasi.views;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VwUser {
    String userName;
    String name;
    String avatar;
}
