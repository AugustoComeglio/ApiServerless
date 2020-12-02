package com.api.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.api.entities.Persona;
import org.json.JSONObject;

import javax.ws.rs.BadRequestException;
import java.util.List;

public interface PersonaRepository {

    JSONObject createPersona(Persona persona) throws DynamoDBMappingException;

    JSONObject getOnePersona(String id) throws DynamoDBMappingException;

    JSONObject updatePersona(Persona persona) throws DynamoDBMappingException;

    JSONObject deletePersona(String id) throws DynamoDBMappingException;

    JSONObject getAllPersonas() throws DynamoDBMappingException;


}
