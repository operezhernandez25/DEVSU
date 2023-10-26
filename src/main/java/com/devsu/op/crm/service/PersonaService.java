package com.devsu.op.crm.service;

import org.springframework.stereotype.Service;

import com.devsu.op.crm.entity.Persona;
import com.devsu.op.crm.exceptions.IdentificacionExistenteException;
import com.devsu.op.crm.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {
    
    private final PersonaRepository personaRepository;

    public Persona savePersona(Persona persona) {
        Persona existente = obtenerPorIdentificacion(persona.getIdentificacion());
        if(existente != null) {
            throw new IdentificacionExistenteException("La identificación ya existe en la base de datos.");
        }
        return personaRepository.save(persona);
    }

    public Persona obtenerPorIdentificacion(String identificacion) {
        return personaRepository.findByIdentificacion(identificacion)
                .orElse(null);
    }
}