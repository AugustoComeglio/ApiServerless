package com.api.services;

import com.api.entities.Persona;
import com.api.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PerosonaService{


    @Autowired
    private PersonaRepository personaRepository;

    public Persona createPersona(Persona persona) {
        return personaRepository.createPersona(persona);
    }

    public Persona getOnePersona(String id) {
        return personaRepository.getOnePersona(id);
    }

    @Override
    public Persona updatePersona(Persona persona) {
        return personaRepository.updatePersona(persona);
    }

    @Override
    public void deletePersona(String id) {
        personaRepository.deletePersona(id);
    }

    @Override
    public List<Persona> getAllPersonas() {
        return personaRepository.getAllPersonas();
    }
}
