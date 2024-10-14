package com.muhammet.arkadaslik_uygulamasi.repository;

import com.muhammet.arkadaslik_uygulamasi.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {

    List<Follow> findAllByUserId(Long userId);

    @Query("select f.followId from Follow f where f.userId= ?1")
    List<Long> findAllFollowIdByUserId(Long userId);
}
