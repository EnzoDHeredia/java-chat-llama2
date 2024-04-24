package com.chatgpt.chatgpt.controller;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatgpt.chatgpt.entities.Mensaje;
import com.chatgpt.chatgpt.service.MensajeriaService;

@Controller
public class ChatController {

    private final OllamaChatClient chatClient;

    @Autowired
    private MensajeriaService mensajeService;

    //@Autowired
    public ChatController(OllamaChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/form")
    public String showForm(Model model) {

        model.addAttribute("mensajes", mensajeService.obtenerTodosLosMensajes());
        return "form";
    }

    @PostMapping("/form")
    public String processForm(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message, Model model) {

        Mensaje mensaje = new Mensaje();
        mensaje.setRemitente("Usuario");
        mensaje.setEsBot(false);
        mensaje.setMensaje(message);
        

        //model.addAttribute("message", new Message(message));
        Mensaje mensaje1 = new Mensaje();
        mensaje1.setRemitente("Ollama");
        mensaje1.setEsBot(true);
        mensaje1.setMensaje(this.chatClient.call(message));

        mensajeService.guardarMensaje(mensaje);
        mensajeService.guardarMensaje(mensaje1);
        

        model.addAttribute("mensajes", mensajeService.obtenerTodosLosMensajes());
        return "result"; 
    }

}
