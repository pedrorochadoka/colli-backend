package br.com.colli.service;

import br.com.colli.model.Cor;
import br.com.colli.repository.CorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CorService {

    @Inject
    CorRepository corRepository;

    public List<Cor> listarCores() {
        return corRepository.listarTodas();
    }

}
