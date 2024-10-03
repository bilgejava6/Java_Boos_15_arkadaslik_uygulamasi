package com.muhammet.arkadaslik_uygulamasi.repository;

import com.muhammet.arkadaslik_uygulamasi.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {
}
