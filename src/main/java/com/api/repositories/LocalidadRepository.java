package com.api.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.api.entities.Localidad;
import com.api.entities.Persona;

import java.util.List;

public interface LocalidadRepository {

    Localidad createLocalidad(Localidad localidad) throws DynamoDBMappingException;

    Localidad getOneLocalidad(String id) throws DynamoDBMappingException;

    Localidad updateLocalidad(Localidad localidad) throws DynamoDBMappingException;

    void deleteLocalidad(String id) throws DynamoDBMappingException;

    List<Localidad> getAllLocalidades() throws DynamoDBMappingException;


}
