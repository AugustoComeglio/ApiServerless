package com.api.repositories;

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

@Component
public class AutorRepositoryImpl implements AutorRepository{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Override
    public Autor createAutor(Autor autor) {
        dynamoDBMapper.save(autor);
        return autor;
    }

    @Override
    public Autor getOneAutor(String id) {
        Autor autor = dynamoDBMapper.load(Autor.class,id);
        return autor;
    }

    @Override
    public Autor updateAutor(Autor autor) {
        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();
        ExpectedAttributeValue expectedAttributeValue = new ExpectedAttributeValue(new AttributeValue().withS(autor.getId()));
        expectedAttributeValueMap.put("id", expectedAttributeValue);
        DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression().withExpected(expectedAttributeValueMap);
        dynamoDBMapper.save(autor,saveExpression);
        return autor;
    }

    @Override
    public void deleteAutor (String id) {
        Autor autor = dynamoDBMapper.load(Autor.class,id);
        dynamoDBMapper.delete(autor);
    }

    @Override
    public List<Autor> getAllAutores() {
        List<Autor> autores = dynamoDBMapper.scan(Autor.class, new DynamoDBScanExpression());
        return autores;
    }
}
