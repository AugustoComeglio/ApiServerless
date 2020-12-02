package com.api.repositories;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.s3.model.JSONInput;
import com.api.entities.Libro;
import com.api.entities.Persona;
import com.api.pojos.ResponseDomicilo;
import com.api.pojos.ResponseLibro;
import com.api.pojos.ResponsePersona;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.stereotype.Component;

import java.util.*;

import org.json.*;


@Component
public class PersonaRepositoryImpl implements PersonaRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public JSONObject createPersona(Persona persona) throws DynamoDBMappingException {
        JSONObject respuesta = new JSONObject();
        try{
            dynamoDBMapper.save(persona);
            respuesta.put("mensaje","Persona creada");
            ResponseDomicilo dom = new ResponseDomicilo(persona.getDomicilio().getCalle(), persona.getDomicilio().getNumero());
            List<ResponseLibro> libros = new ArrayList<ResponseLibro>();
            for (Libro libro : persona.getLibros()) {
                ResponseLibro l = new ResponseLibro(libro.getTitulo(),libro.getGenero(), libro.getPaginas(),libro.getFecha());
                libros.add(l);
            }
            ResponsePersona persona1 = new ResponsePersona(persona.getId(),persona.getApellido(),persona.getNombre(),persona.getDni(),dom,libros);
            Gson gson = new GsonBuilder().create();
            String jsonPersona = gson.toJson(persona1);
            respuesta.put("data",new JSONObject(jsonPersona));
            return respuesta;
        }catch (DynamoDBMappingException e ){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public JSONObject getOnePersona(String id) throws DynamoDBMappingException {
        JSONObject respuesta = new JSONObject();
        try{
            Persona persona = dynamoDBMapper.load(Persona.class,id);
            respuesta.put("mensaje", "Persona encontrada");
            ResponseDomicilo dom = new ResponseDomicilo(persona.getDomicilio().getCalle(), persona.getDomicilio().getNumero());
            List<ResponseLibro> libros = new ArrayList<ResponseLibro>();
            for (Libro libro : persona.getLibros()) {
                ResponseLibro l = new ResponseLibro(libro.getTitulo(),libro.getGenero(), libro.getPaginas(),libro.getFecha());
                libros.add(l);
            }
            ResponsePersona persona1 = new ResponsePersona(persona.getId(),persona.getApellido(),persona.getNombre(),persona.getDni(),dom,libros);
            Gson gson = new GsonBuilder().create();
            String jsonPersona = gson.toJson(persona1);
            respuesta.put("data",new JSONObject(jsonPersona));
            return respuesta;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public JSONObject updatePersona(Persona persona) throws DynamoDBMappingException{
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(persona.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        JSONObject respuesta = new JSONObject();
        try{
            dynamoDBMapper.save(persona,saveExpression);
            respuesta.put("mensaje", "Persona actualizada");
            ResponseDomicilo dom = new ResponseDomicilo(persona.getDomicilio().getCalle(), persona.getDomicilio().getNumero());
            List<ResponseLibro> libros = new ArrayList<ResponseLibro>();
            for (Libro libro : persona.getLibros()) {
                ResponseLibro l = new ResponseLibro(libro.getTitulo(),libro.getGenero(), libro.getPaginas(),libro.getFecha());
                libros.add(l);
            }
            ResponsePersona persona1 = new ResponsePersona(persona.getId(),persona.getApellido(),persona.getNombre(),persona.getDni(),dom,libros);
            Gson gson = new GsonBuilder().create();
            String jsonPersona = gson.toJson(persona1);
            respuesta.put("data",new JSONObject(jsonPersona));
            return respuesta;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }


    @Override
    public JSONObject deletePersona (String id) throws DynamoDBMappingException{
        JSONObject respuesta = new JSONObject();
        try{
            Persona persona = dynamoDBMapper.load(Persona.class,id);
                if ( persona != null){
                dynamoDBMapper.delete(persona);
                respuesta.put("mensaje","persona eliminada");
                } else {
                    throw new DynamoDBMappingException();
                }
                return respuesta;
            /*
             Optional<Persona> persona = Optional.ofNullable(getOnePersona(id));
             if (persona.isPresent()) {
                 dynamoDBMapper.delete(persona.get());
             }
             else {
                 throw new DynamoDBMappingException();
             }
             */
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }

    }

    @Override
    public JSONObject getAllPersonas() throws DynamoDBMappingException{
        JSONObject respuesta = new JSONObject();
        try{
            List<Persona> personas = dynamoDBMapper.scan(Persona.class, new DynamoDBScanExpression());

            JSONArray pers = new JSONArray();
            for (Persona p : personas) {
                Gson gson = new GsonBuilder().create();
                String jsonPersona = gson.toJson(p);
                JSONObject personajson = new JSONObject(jsonPersona);
                pers.put(personajson);
            }
            respuesta.put("data",pers);
            respuesta.put("mensaje", "Personas encontrdas");
            return respuesta;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }
}
