package br.com.colli.service;

import br.com.colli.model.Imagem;
import br.com.colli.model.Produto;
import br.com.colli.util.ImageCompressionUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
public class ImagemService {

    @PersistenceContext
    private EntityManager em;

    public Imagem salvarImagemParaProduto(Produto produto, InputStream imagemStream) throws IOException {
        // Compactar a imagem antes de salvar
        byte[] imagemCompactada = ImageCompressionUtil.compressImage(imagemStream, 0.8f);  // 0.8f indica 80% de qualidade

        // Criar a imagem associada ao produto
        Imagem imagem = new Imagem();
        imagem.setProduto(produto);
        imagem.setImagem(imagemCompactada);

        // Persistir a imagem
        em.persist(imagem);

        return imagem;
    }
}