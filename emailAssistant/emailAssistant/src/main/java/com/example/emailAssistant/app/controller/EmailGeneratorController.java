package com.example.emailAssistant.app.controller;


import com.example.emailAssistant.app.repo.EmailRepository;
import com.example.emailAssistant.app.service.EmailGeneratorService;
import com.example.emailAssistant.app.dto.EmailRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins ="*")

public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;
    private final EmailRepository repo;

    public EmailGeneratorController(EmailGeneratorService emailGeneratorService, EmailRepository repo) {
        this.emailGeneratorService = emailGeneratorService;
        this.repo = repo;
    }

    @PostMapping("/generate")
    public Mono<String> generateEmail(@RequestBody EmailRequest emailrequest) {
        return emailGeneratorService.generateReply(emailrequest);
    }
}







