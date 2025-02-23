package br.com.colli.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class Imagem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imagem_id")
    private Long imagemId;

    @OneToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "produto_id")
    @JsonBackReference
    private Produto produto;

    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "imagem", nullable = false)
    private byte[] imagem;

    // Getters e Setters
    public Long getImagemId() {
        return imagemId;
    }

    public void setImagemId(Long imagemId) {
        this.imagemId = imagemId;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }
}

