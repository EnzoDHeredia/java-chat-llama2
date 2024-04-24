package com.chatgpt.chatgpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatgpt.chatgpt.entities.Mensaje;
import com.chatgpt.chatgpt.repositories.MensajeriaRepository;

@Service
public class MensajeriaService {

    @Autowired
    private MensajeriaRepository mensajeriaRepository;

    public void guardarMensaje(Mensaje mensaje) {
        mensajeriaRepository.save(mensaje);
    }

    public List<Mensaje> obtenerTodosLosMensajes(){
        return mensajeriaRepository.findAll();
    }
}
