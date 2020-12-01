package com.api.repositories;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.api.entities.Autor;
import com.api.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AutorRepositoryImpl implements AutorRepository{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Autor createAutor(Autor autor) throws DynamoDBMappingException {
        try{
            dynamoDBMapper.save(autor);
            return autor;
        }catch (DynamoDBMappingException e ){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public Autor getOneAutor(String id) throws DynamoDBMappingException {
        try{
            Autor autor = dynamoDBMapper.load(Autor.class,id);
            return autor;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());

        }
    }

    @Override
    public Autor updateAutor(Autor autor) throws DynamoDBMappingException {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(autor.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        try{
            dynamoDBMapper.save(autor,saveExpression);
            return autor;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }

    @Override
    public void deleteAutor (String id) throws DynamoDBMappingException {
        try{
            Autor autor = dynamoDBMapper.load(Autor.class,id);
            dynamoDBMapper.delete(autor);
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }


    }

    @Override
    public List<Autor> getAllAutores() throws DynamoDBMappingException {
        try{
            List<Autor> autores = dynamoDBMapper.scan(Autor.class, new DynamoDBScanExpression());
            return autores;
        }catch (DynamoDBMappingException e){
            throw new DynamoDBMappingException(e.getMessage());
        }
    }
}
