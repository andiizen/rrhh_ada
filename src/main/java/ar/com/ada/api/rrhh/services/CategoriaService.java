package ar.com.ada.api.rrhh.services;

import java.util.*;

import ar.com.ada.api.rrhh.entities.*;
import ar.com.ada.api.rrhh.repos.CategoriaRepository;

public class CategoriaService {

    CategoriaRepository repository;

    public void crearCategoria(Categoria categoria) {
        repository.save(categoria);

    }

    public List<Categoria> listarCategorias() {

        return repository.findAll();

    }
}