package com.api.services;

import com.api.entities.Autor;
import java.util.List;

public interface AutorService {

    Autor createAutor(Autor autor);

    Autor getOneAutor(String id);

    Autor updateAutor(Autor autor);

    void deleteAutor(String id);

    List<Autor> getAllAutores();
}

