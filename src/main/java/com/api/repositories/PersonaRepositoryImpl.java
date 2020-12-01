package com.api.repositories;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.api.entities.Localidad;
import com.api.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Component
public class PersonaRepositoryImpl implements PersonaRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Persona createPersona(Persona persona) throws DynamoDBMappingException {
        try{
            dynamoDBMapper.save(persona);
            return persona;
        }catch (DynamoDBMappingException e ){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public Persona getOnePersona(String id) throws DynamoDBMappingException {
        try{
            Persona persona = dynamoDBMapper.load(Persona.class,id);
            return persona;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public Persona updatePersona(Persona persona) throws DynamoDBMappingException{
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(persona.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        try{
            dynamoDBMapper.save(persona,saveExpression);
            return persona;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public void deletePersona (String id) throws DynamoDBMappingException{
        try{
            /*
            Persona persona = dynamoDBMapper.load(Persona.class,id);;
            if (persona != null){
                dynamoDBMapper.delete(persona);
            }
            else{
                throw new DynamoDBMappingException();
            }

             */

             Optional<Persona> persona = Optional.ofNullable(getOnePersona(id));
             if (persona.isPresent()) {
                 dynamoDBMapper.delete(persona.get());
             }
             else {
                 throw new DynamoDBMappingException();
             }

        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }

    }

    @Override
    public List<Persona> getAllPersonas() throws DynamoDBMappingException{
        try{
            List<Persona> personas = dynamoDBMapper.scan(Persona.class, new DynamoDBScanExpression());
            return personas;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }


    }
}
