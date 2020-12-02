package com.api.services;

import com.api.entities.Persona;
import com.api.repositories.PersonaRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaServiceImpl implements PerosonaService{


    @Autowired
    private PersonaRepository personaRepository;

    public JSONObject createPersona(Persona persona) {
        return personaRepository.createPersona(persona);


    }

    public JSONObject getOnePersona(String id) {
        return personaRepository.getOnePersona(id);
    }

    @Override
    public JSONObject updatePersona(Persona persona) {
        return personaRepository.updatePersona(persona);
    }

    @Override
    public JSONObject deletePersona(String id) {
        return personaRepository.deletePersona(id);
    }

    @Override
    public JSONObject getAllPersonas() {
        return personaRepository.getAllPersonas();
    }
}
