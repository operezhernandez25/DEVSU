package com.devsu.op.crm.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsu.op.crm.entity.Persona;
import com.devsu.op.crm.repository.PersonaRepository;
import com.devsu.op.crm.service.PersonaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/personas")
@RequiredArgsConstructor
public class PersonaController {

    private final PersonaService personaService;

    @PostMapping
    public Persona createPersona(@Valid @RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }
    
}
