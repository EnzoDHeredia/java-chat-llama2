package com.chatgpt.chatgpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatgpt.chatgpt.entities.Conversacion;
import com.chatgpt.chatgpt.repositories.ConversacionRepository;

@Service
public class MensajeriaService {

    @Autowired
    private ConversacionRepository conversacionRepository;

    public List<Conversacion> obtenerTodasLasConversaciones() {
        return conversacionRepository.findAll();
    }

    public Conversacion obtenerConversacionPorId(Long id) {
        return conversacionRepository.findById(id).orElse(null);
    }

    public void guardarConversacion(Conversacion conversacion) {
        conversacionRepository.save(conversacion);
    }
}
