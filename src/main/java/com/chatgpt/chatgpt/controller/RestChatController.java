package com.chatgpt.chatgpt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatClient;


@RestController
@RequestMapping("/chats")
public class RestChatController {

    private final OllamaChatClient chatClient;

    //@Autowired
    public RestChatController(OllamaChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping
    public String generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        //Prompt prompt = new Prompt(new UserMessage(message));
        return this.chatClient.call(message);
    }
}