package br.com.colli.model;


import jakarta.persistence.*;

@Entity(name = "tipo_produto")
public class TipoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_produto_id")
    private Long tipoProdutoId;

    private String nome;

    // Getters e Setters
    public Long getTipoProdutoId() {
        return tipoProdutoId;
    }

    public void setTipoProdutoId(Long tipoProdutoId) {
        this.tipoProdutoId = tipoProdutoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

