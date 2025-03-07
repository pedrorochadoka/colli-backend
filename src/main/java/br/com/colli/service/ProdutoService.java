package br.com.colli.service;


import br.com.colli.model.Imagem;
import br.com.colli.model.Produto;
import br.com.colli.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;
    @Inject
    ImagemService imagemService;
    @PersistenceContext
    private EntityManager em;
    @Inject
    ObjectMapper mapper;

    // Criar um novo produto
    @Transactional
    public Produto criarProduto(Produto produto) {
        System.out.println(produto.toString());
        produtoRepository.persist(produto);
        return produto;
    }

    @Transactional
    public Produto criarProdutoComImagem(String produtoJson, InputStream imagemStream) throws IOException {
        Produto produto = mapper.readValue(produtoJson, Produto.class);

        produto.setDataCadastro(LocalDateTime.now());
        em.persist(produto);
        em.flush();
        try{
            Imagem imagem = imagemService.salvarImagemParaProduto(produto, imagemStream);
            produto.setImagem(imagem);
        }catch(Exception e){
            Logger.getLogger(ProdutoService.class.getName()).log(Level.SEVERE, "Erro salvar a imagem", e.getMessage());
        }

        return produto;
    }

    // Obter um produto pelo ID
    @Transactional
    public Optional<Produto> obterProdutoPorId(Long produtoId) {
        return produtoRepository.findByIdOptional(produtoId);
    }

    // Obter todos os produtos
    @Transactional
    public List<Produto> obterTodosProdutos() {
        return produtoRepository.listAll();
    }

    // Atualizar um produto
    @Transactional
    public Optional<Produto> atualizarProduto(Long produtoId, Produto produtoAtualizado) {
        Optional<Produto> produto = produtoRepository.findByIdOptional(produtoId);
        if (produto.isPresent()) {
            Produto p = produto.get();
            p.setNome(produtoAtualizado.getNome());
            p.setDescricao(produtoAtualizado.getDescricao());
            p.setPreco(produtoAtualizado.getPreco());
            p.setDataCadastro(produtoAtualizado.getDataCadastro());
            p.setDataCompra(produtoAtualizado.getDataCompra());
            p.setTipoProduto(produtoAtualizado.getTipoProduto());
            p.setCategoria(produtoAtualizado.getCategoria());
            p.setCor(produtoAtualizado.getCor());
            return Optional.of(p);
        }
        return Optional.empty();
    }

    // Deletar um produto
    @Transactional
    public boolean deletarProduto(Long produtoId) {
        Optional<Produto> produto = produtoRepository.findByIdOptional(produtoId);
        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
            return true;
        }
        return false;
    }
}
