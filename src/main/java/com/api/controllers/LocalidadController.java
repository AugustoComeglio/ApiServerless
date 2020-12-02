package com.api.controllers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.api.entities.Autor;
import com.api.entities.Localidad;
import com.api.entities.Persona;
import com.api.services.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/localidad")
public class LocalidadController{

    @Autowired
    LocalidadService localidadService;

    @PostMapping()
    public ResponseEntity createLocalidad(@RequestBody Localidad localidad){
        try {
            Localidad respuesta = localidadService.createLocalidad(localidad);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }catch (AmazonDynamoDBException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity getOneLocalidad(@RequestBody String id){
        try{
            Localidad respuesta = localidadService.getOneLocalidad(id);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonDynamoDBException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }

    }

    @PutMapping()
    public ResponseEntity updateLocalidad(@RequestBody Localidad localidad){
        try{
            Localidad respuesta = localidadService.updateLocalidad(localidad);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonDynamoDBException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteLocalidad(@RequestBody String id){
        try{
            localidadService.deleteLocalidad(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (AmazonDynamoDBException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }

    }

    @GetMapping()
    public ResponseEntity getAllLocalidades(){
        try{
            List<Localidad> respuesta = localidadService.getAllLocalidades();
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonDynamoDBException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }
}
