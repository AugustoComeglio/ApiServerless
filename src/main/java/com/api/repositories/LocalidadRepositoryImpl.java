package com.api.repositories;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.api.entities.Autor;
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
    public Localidad createLocalidad(Localidad localidad) throws DynamoDBMappingException{
        try{
            dynamoDBMapper.save(localidad);
            return localidad;
        }catch (DynamoDBMappingException e ){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public Localidad getOneLocalidad(String id) throws DynamoDBMappingException{
        try{
            Localidad  localidad = dynamoDBMapper.load(Localidad.class,id);
            return localidad;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public Localidad updateLocalidad(Localidad localidad) throws DynamoDBMappingException {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(localidad.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        try{
            dynamoDBMapper.save(localidad,saveExpression);
            return localidad;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }

    }

    @Override
    public void deleteLocalidad (String id) throws DynamoDBMappingException{
        try{
            Localidad localidad = dynamoDBMapper.load(Localidad.class,id);;
            if (localidad != null){
                dynamoDBMapper.delete(localidad);
            }
            else{
                throw new DynamoDBMappingException();
            }
            /*

             Optional<Localidad> localidad = getOnePersona(id);
             if (localidad.isPresent()) {
                 dynamoDBMapper.delete(localidad.get());
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
    public List<Localidad> getAllLocalidades() throws DynamoDBMappingException{
        try{
            List<Localidad> localidades = dynamoDBMapper.scan(Localidad.class, new DynamoDBScanExpression());
            return localidades;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

}
