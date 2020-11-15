package com.api.services;

import com.api.entities.Autor;
import com.api.repositories.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AutorServiceImpl implements AutorService{

    @Autowired
    private AutorRepository autorRepository;

    public Autor createAutor(Autor autor) {
        return autorRepository.createAutor(autor);
    }

    public Autor getOneAutor(String id) {
        return autorRepository.getOneAutor(id);
    }

    @Override
    public Autor updateAutor(Autor autor) {
        return autorRepository.updateAutor(autor);
    }

    @Override
    public void deleteAutor(String id) {
        autorRepository.deleteAutor(id);
    }

    @Override
    public List<Autor> getAllAutores() {
        return autorRepository.getAllAutores();
    }
}
