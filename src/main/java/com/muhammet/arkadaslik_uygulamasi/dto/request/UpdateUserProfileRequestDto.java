package com.muhammet.arkadaslik_uygulamasi.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserProfileRequestDto {
    @NotNull
    Long id;
    @Size(min = 2, max = 100)
    String name;
    String avatar;
    @Size(min = 2, max = 64)
    String userName;
    @NotEmpty
    @Size(min = 8, max = 64)
    @Pattern(
            message = "Şifreniz en az 8 en fazla 64 karakter olmalı, Şirenizde En az Bir büyük bir küçük harf ve özel karakter olmalıdır.",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$"
    )
    String password;
    String email;
    String phone;
    Integer age;
}
