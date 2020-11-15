package com.api.controllers;

import com.api.entities.Autor;
import com.api.entities.Persona;
import com.api.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController{

    @Autowired
    AutorService autorService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createAutor(@RequestBody Autor autor){
        try {
            Autor respuesta = autorService.createAutor(autor);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/id")
    public ResponseEntity getOneAutor(@RequestBody String id){
        try {
            Autor respuesta = autorService.getOneAutor(id);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateAutor(@RequestBody Autor autor){
        try {
            Autor respuesta = autorService.updateAutor(autor);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteAutor(@RequestBody String id){
        try {
            autorService.deleteAutor(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("")
    public ResponseEntity getAllAutores(){
        try {
            List<Autor> respuesta = autorService.getAllAutores();
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }
}
