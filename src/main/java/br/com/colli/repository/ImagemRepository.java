package br.com.colli.repository;

import br.com.colli.model.Imagem;
import br.com.colli.service.ProdutoService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


    @ApplicationScoped
    public class ImagemRepository implements PanacheRepository<Imagem> {

        public List<Imagem> findByCor(String corNome) {
            List<Imagem> imagens = find("produto.cor.nome", corNome).list();
            Logger.getLogger(ImagemRepository.class.getName()).log(Level.INFO, "Qtd imagens por cor", imagens.size());

            List<Imagem> imagens2 = find("SELECT i FROM Imagem i JOIN i.produto p JOIN p.cor c WHERE c.nome = ?1", corNome).list();
            Logger.getLogger(ImagemRepository.class.getName()).log(Level.INFO, "Qtd imagens por cor", imagens2.size());

            return imagens;
        }

    }
