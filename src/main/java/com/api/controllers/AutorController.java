package com.api.controllers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceResponse;
import com.api.entities.Autor;
import com.api.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController{

    @Autowired
    AutorService autorService;

    @PostMapping()
    public ResponseEntity createAutor(@RequestBody Autor autor){
        try {
            Autor respuesta = autorService.createAutor(autor);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @GetMapping("/id")
    public ResponseEntity getOneAutor(@RequestBody String id){
        try{
            Autor respuesta = autorService.getOneAutor(id);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @PutMapping()
    public ResponseEntity updateAutor(@RequestBody Autor autor){
        try{
            Autor respuesta = autorService.updateAutor(autor);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }


    @DeleteMapping("/id")
    public ResponseEntity deleteAutor(@RequestBody String id){
        try{
            autorService.deleteAutor(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }

    @GetMapping("")
    public ResponseEntity getAllAutores(){
        try{
            List<Autor> respuesta = autorService.getAllAutores();
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (AmazonServiceException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.getStatusCode()), e.getMessage(), e);
        }catch (AmazonClientException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(),e);
        }
    }
}