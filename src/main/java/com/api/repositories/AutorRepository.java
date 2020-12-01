package com.api.repositories;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMappingException;
import com.api.entities.Autor;
import java.util.List;

public interface AutorRepository {

    Autor createAutor(Autor autor) throws DynamoDBMappingException;

    Autor getOneAutor(String id) throws DynamoDBMappingException;

    Autor updateAutor(Autor autor) throws DynamoDBMappingException;

    void deleteAutor(String id) throws DynamoDBMappingException;

    List<Autor> getAllAutores() throws DynamoDBMappingException;
}
