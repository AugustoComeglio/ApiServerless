package com.api.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.api.entities.Persona;
import java.util.List;

public interface PersonaRepository {

    Persona createPersona(Persona persona) throws DynamoDBMappingException;

    Persona getOnePersona(String id) throws DynamoDBMappingException;

    Persona updatePersona(Persona persona) throws DynamoDBMappingException;

    void deletePersona(String id) throws DynamoDBMappingException;

    List<Persona> getAllPersonas() throws DynamoDBMappingException;


}
