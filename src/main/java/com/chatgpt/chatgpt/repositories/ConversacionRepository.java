package com.chatgpt.chatgpt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chatgpt.chatgpt.entities.Conversacion;

public interface ConversacionRepository extends JpaRepository<Conversacion, Long>{

}
