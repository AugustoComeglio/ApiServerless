package com.api.repositories;

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
        dynamoDBMapper.save(persona);
        return persona;
    }

    @Override
    public Persona getOnePersona(String id) {
        Persona  persona = dynamoDBMapper.load(Persona.class,id);
        return persona;
    }

    @Override
    public Persona updatePersona(Persona persona) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(persona.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(persona,saveExpression);
        return persona;
    }

    @Override
    public void deletePersona (String personaId) {
        Persona persona = dynamoDBMapper.load(Persona.class,personaId);
        dynamoDBMapper.delete(persona);

    }

    @Override
    public List<Persona> getAllPersonas() {
        List<Persona> personas = dynamoDBMapper.scan(Persona.class, new DynamoDBScanExpression());
        return personas;
    }
}
