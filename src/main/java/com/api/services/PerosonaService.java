package com.api.services;

import com.api.entities.Persona;
import org.json.JSONObject;

import java.util.List;

public interface PerosonaService {

    JSONObject createPersona(Persona persona) ;

    JSONObject getOnePersona(String id);

    JSONObject updatePersona(Persona persona);

    JSONObject deletePersona(String id);

    JSONObject getAllPersonas();

}
