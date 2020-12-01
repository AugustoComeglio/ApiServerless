package com.api.controllers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.api.entities.Autor;
import com.api.entities.Persona;
import com.api.services.PerosonaService;
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
    public ResponseEntity createPersona(@RequestBody Persona persona){
        try {
            Persona respuesta = personaService.createPersona(persona);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity getOnePersona(@RequestBody String id){
        try{
            Persona respuesta = personaService.getOnePersona(id);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @PutMapping()
    public ResponseEntity updatePersona(@RequestBody Persona persona){
        try{
            Persona respuesta = personaService.updatePersona(persona);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity deletePersona(@RequestBody String id){
        try{
            personaService.deletePersona(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @GetMapping()
    public ResponseEntity getAllPersonas(){
        try{
            List<Persona> respuesta = personaService.getAllPersonas();
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }
}
