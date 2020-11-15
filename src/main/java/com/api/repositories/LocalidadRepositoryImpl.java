package com.api.repositories;

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

@Component
public class LocalidadRepositoryImpl implements LocalidadRepository{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Localidad createLocalidad(Localidad localidad) {
        dynamoDBMapper.save(localidad);
        return localidad;
    }

    @Override
    public Localidad getOneLocalidad(String id) {
        Localidad  localidad = dynamoDBMapper.load(Localidad.class,id);
        return localidad;
    }

    @Override
    public Localidad updateLocalidad(Localidad localidad) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(localidad.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(localidad,saveExpression);
        return localidad;
    }

    @Override
    public void deleteLocalidad (String id) {
        Localidad localidad = dynamoDBMapper.load(Localidad.class,id);
        dynamoDBMapper.delete(localidad);
    }

    @Override
    public List<Localidad> getAllLocalidades() {
       List<Localidad> localidades = dynamoDBMapper.scan(Localidad.class, new DynamoDBScanExpression());
       return localidades;
    }

}
