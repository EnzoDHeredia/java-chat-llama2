package com.chatgpt.chatgpt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatgpt.chatgpt.entities.Mensaje;

public interface MensajeriaRepository extends JpaRepository<Mensaje, Long> {

}
