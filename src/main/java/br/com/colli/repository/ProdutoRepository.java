package br.com.colli.repository;

import br.com.colli.model.Produto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {
    // Aqui você pode adicionar consultas personalizadas se necessário
}
