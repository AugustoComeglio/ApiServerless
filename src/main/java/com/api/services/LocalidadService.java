package com.api.services;

import com.api.entities.Localidad;
import com.api.entities.Persona;

import java.util.List;

public interface LocalidadService {


    Localidad createLocalidad(Localidad localiad);

    Localidad getOneLocalidad(String id);

    Localidad updateLocalidad(Localidad localidad);

    void deleteLocalidad(String id);

    List<Localidad> getAllLocalidades();


}
