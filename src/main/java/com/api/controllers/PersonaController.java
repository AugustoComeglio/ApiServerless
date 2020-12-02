package com.api.controllers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.api.entities.Autor;
import com.api.entities.Persona;
import com.api.services.PerosonaService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/persona")
public class PersonaController  {

    @Autowired
    PerosonaService personaService;

    @PostMapping()
    public ResponseEntity createPersona(@RequestBody Persona persona) throws Exception{
        JSONObject respuesta = personaService.createPersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta.toString());
    }


    @GetMapping("/id")
    public ResponseEntity getOnePersona(@RequestBody String id){
        JSONObject respuesta = personaService.getOnePersona(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta.toString());
    }

    @PutMapping()
    public ResponseEntity updatePersona(@RequestBody Persona persona){
        JSONObject respuesta = personaService.updatePersona(persona);
       return ResponseEntity.status(HttpStatus.OK).body(respuesta.toString());
    }

    @DeleteMapping("/id")
    public ResponseEntity deletePersona(@RequestBody String id){
        JSONObject respuesta = personaService.deletePersona(id);
        return ResponseEntity.status(HttpStatus.OK).body(respuesta.toString());
    }

    @GetMapping()
    public ResponseEntity getAllPersonas(){
        JSONObject respuesta = personaService.getAllPersonas();
        return ResponseEntity.status(HttpStatus.OK).body(respuesta.toString());

    }
}
