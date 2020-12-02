package com.api.pojos;

import com.api.entities.Libro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponsePersona implements Serializable {

    private String id;
    private String apellido;
    private String nombre;
    private Integer dni;
    private ResponseDomicilo domicilio;
    private List<ResponseLibro> libros = new ArrayList<ResponseLibro>();


    public ResponsePersona(String id, String apellido, String nombre, Integer dni, ResponseDomicilo domicilio, List<ResponseLibro> libros) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.libros = libros;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public ResponsePersona() {
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
