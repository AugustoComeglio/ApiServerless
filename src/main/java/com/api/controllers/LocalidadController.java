package com.api.controllers;

import com.api.entities.Localidad;
import com.api.entities.Persona;
import com.api.services.LocalidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/localidad")
public class LocalidadController{

    @Autowired
    LocalidadService localidadService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity createLocalidad(@RequestBody Localidad localidad){
        try {
            Localidad respuesta = localidadService.createLocalidad(localidad);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("/id")
    public ResponseEntity getOneLocalidad(@RequestBody String id){
        try {
            Localidad respuesta = localidadService.getOneLocalidad(id);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateLocalidad(@RequestBody Localidad localidad){
        try {
            Localidad respuesta = localidadService.updateLocalidad(localidad);
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity deleteLocalidad(@RequestBody String id){
        try {
            localidadService.deleteLocalidad(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }

    @GetMapping("")
    public ResponseEntity getAllLocalidades(){
        try {
            List<Localidad> respuesta = localidadService.getAllLocalidades();
            return ResponseEntity.status(HttpStatus.OK).body(respuesta);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. por favor intente mas tarde.\"}");
        }
    }


}
