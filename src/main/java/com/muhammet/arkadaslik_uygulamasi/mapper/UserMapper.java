package com.muhammet.arkadaslik_uygulamasi.mapper;

import com.muhammet.arkadaslik_uygulamasi.dto.request.UpdateUserProfileRequestDto;
import com.muhammet.arkadaslik_uygulamasi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Herhangi bir DTO dan User entity sine dönüştürmek işlemi için kullanılacak
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    // Bu interface üzerinden oluşturulacak olan IMPL sınıfının nesnesini yaratarak
    // referans adresini atamaka için kullanıyoruz.
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User eyyyyDtoUserNesnesineDonus(final UpdateUserProfileRequestDto dto);

}
