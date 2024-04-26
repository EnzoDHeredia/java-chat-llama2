package com.chatgpt.chatgpt.controller;

import org.springframework.ai.ollama.OllamaChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatgpt.chatgpt.entities.Conversacion;
import com.chatgpt.chatgpt.entities.Mensaje;
import com.chatgpt.chatgpt.service.MensajeriaService;

@Controller
public class ChatController {

    private final OllamaChatClient chatClient;

    @Autowired
    private MensajeriaService mensajeService;

    // @Autowired
    public ChatController(OllamaChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/conversaciones")
    public String listarConversaciones(Model model) {
        model.addAttribute("conversaciones", mensajeService.obtenerTodasLasConversaciones());
        return "conversaciones";
    }

    @GetMapping("/chat")
    public String showForm(@RequestParam(value = "conversacionId") Long id, Model model) {
        model.addAttribute("conversacion", buscarConversacion(id));
        return "chat";
    }

    @PostMapping("/chat")
    public String processForm(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message,
            Model model, @RequestParam(value = "conversacionId") Long conversacionId) {
        Conversacion conversacion = buscarConversacion(conversacionId);

        Mensaje mensaje = new Mensaje("Usuario", false, message, conversacion);
        conversacion.addMensaje(mensaje);

        Mensaje mensajeBot = new Mensaje("Ollama", true, this.chatClient.call(message), conversacion);
        conversacion.addMensaje(mensajeBot);

        mensajeService.guardarConversacion(conversacion);

        model.addAttribute("conversacion", conversacion);
        return "result";
    }

    @PostMapping("/eliminar-conversacion")
    public String eliminarConversacion(@RequestParam(value = "conversacionId") Long id) {
        mensajeService.eliminarConversacionPorId(id);
        return "redirect:/conversaciones";
    }

    public Conversacion buscarConversacion(Long conversacionId) {
        if (conversacionId != -1L) {
            Conversacion conversacion = mensajeService.obtenerConversacionPorId(conversacionId);
            return conversacion;
        } else {
            Conversacion conversacion = new Conversacion();
            mensajeService.guardarConversacion(conversacion);
            return conversacion;
        }
    }
}
