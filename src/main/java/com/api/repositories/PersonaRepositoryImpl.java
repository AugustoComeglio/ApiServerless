package com.api.repositories;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.api.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class PersonaRepositoryImpl implements PersonaRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Persona createPersona(Persona persona) {
        try {
            dynamoDBMapper.save(persona);
        }catch (AmazonServiceException ase){
            System.err.println("No se pudo completar la operacion");
            System.err.println("Error "+ ase.getMessage());
            System.err.println("Codigo: "+ase.getErrorCode());
        }catch (AmazonClientException ace){
            System.err.println("Error interno al comunicarse con DynamoDB");
            System.err.println("Error "+ace.getMessage());
        }
        return persona;
    }

    @Override
    public Persona getOnePersona(String id) {
        //La inicializo null ya que no puedo retornar una variable que puede no haber sido inicializada
        Persona persona=null;
        try{
            persona = dynamoDBMapper.load(Persona.class,id);
        }catch (AmazonServiceException ase){
            System.err.println("No se pudo completar la operacion");
            System.err.println("Error "+ ase.getMessage());
            System.err.println("Codigo: "+ase.getErrorCode());
        }catch (AmazonClientException ace){
            System.err.println("Error interno al comunicarse con DynamoDB");
            System.err.println("Error "+ace.getMessage());
        }
        return persona;
    }

    @Override
    public Persona updatePersona(Persona persona) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(persona.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        try{
        dynamoDBMapper.save(persona,saveExpression);
        }catch (AmazonServiceException ase){
            System.err.println("No se pudo completar la operacion");
            System.err.println("Error "+ ase.getMessage());
            System.err.println("Codigo: "+ase.getErrorCode());
        }catch (AmazonClientException ace){
            System.err.println("Error interno al comunicarse con DynamoDB");
            System.err.println("Error "+ace.getMessage());
        }
        return persona;
    }

    @Override
    public void deletePersona (String personaId) {
        Persona persona;
        try{
            persona = dynamoDBMapper.load(Persona.class,personaId);
            dynamoDBMapper.delete(persona);
        }catch (AmazonServiceException ase){
            System.err.println("No se pudo completar la operacion");
            System.err.println("Error "+ ase.getMessage());
            System.err.println("Codigo: "+ase.getErrorCode());
        }catch (AmazonClientException ace){
            System.err.println("Error interno al comunicarse con DynamoDB");
            System.err.println("Error "+ace.getMessage());
        }

    }

    @Override
    public List<Persona> getAllPersonas() {
        List<Persona> personas =null;
        try{
        personas = dynamoDBMapper.scan(Persona.class, new DynamoDBScanExpression());
        }catch (AmazonServiceException ase){
            System.err.println("No se pudo completar la operacion");
            System.err.println("Error "+ ase.getMessage());
            System.err.println("Codigo: "+ase.getErrorCode());
        }catch (AmazonClientException ace){
            System.err.println("Error interno al comunicarse con DynamoDB");
            System.err.println("Error "+ace.getMessage());
        }
        return personas;
    }
}
