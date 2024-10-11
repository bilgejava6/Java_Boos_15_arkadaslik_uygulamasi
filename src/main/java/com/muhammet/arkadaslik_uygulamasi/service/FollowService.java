package com.muhammet.arkadaslik_uygulamasi.service;

import com.muhammet.arkadaslik_uygulamasi.dto.request.AddFollowRequestDto;
import com.muhammet.arkadaslik_uygulamasi.entity.Follow;
import com.muhammet.arkadaslik_uygulamasi.exception.ArkadaslikException;
import com.muhammet.arkadaslik_uygulamasi.exception.ErrorType;
import com.muhammet.arkadaslik_uygulamasi.repository.FollowRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowService {

    private final FollowRepository repository;
    private final UserService userService;
    public FollowService(FollowRepository repository, UserService userService){
        this.repository = repository;
        this.userService = userService;
    }


    public void addFollow(AddFollowRequestDto dto) {
        /**
         * şuan -> id: 30 olan kullanıcımız var.
         * userId;= 3000
         * following= 5645646;
         *
         * true -> doğru
         * !true -> yanlış
         */
       if(!userService.existsById(dto.getUserId()) || !userService.existsById(dto.getFollowingId()))
           throw new ArkadaslikException(ErrorType.FOLLOW_USERID_FOLLOWINGID_NOTFOUND);

        repository.save(Follow.builder()
                        .userId(dto.getUserId())
                        .followId(dto.getFollowingId())
                .build());
    }
}
