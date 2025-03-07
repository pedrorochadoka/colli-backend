package br.com.colli.service;

import br.com.colli.model.Categoria;
import br.com.colli.repository.CategoriaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CategoriaService {

    @Inject
    CategoriaRepository categoriaRepository;

    @Transactional
    public List<Categoria> obterTodasCategorias() {
        return categoriaRepository.obterTodasCategorias();
    }
}
