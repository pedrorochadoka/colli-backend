package br.com.colli.repository;

import br.com.colli.model.Cor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CorRepository implements PanacheRepository<Cor> {

    public List<Cor> listarTodas() {
        return listAll();
    }

}
