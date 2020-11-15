package com.api.repositories;

import com.api.entities.Autor;
import java.util.List;

public interface AutorRepository {

    Autor createAutor(Autor autor);

    Autor getOneAutor(String id);

    Autor updateAutor(Autor autor);

    void deleteAutor(String id);

    List<Autor> getAllAutores();
}
