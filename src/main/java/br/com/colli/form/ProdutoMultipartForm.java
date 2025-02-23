package br.com.colli.form;

import org.jboss.resteasy.annotations.providers.multipart.PartType;
import jakarta.ws.rs.FormParam;

import java.io.InputStream;

public class ProdutoMultipartForm {

    @FormParam("produto")
    @PartType("application/json")
    private String produtoJson;

    @FormParam("imagem")
    @PartType("application/octet-stream")
    private InputStream imagemStream;

    public String getProdutoJson() {
        return produtoJson;
    }

    public void setProdutoJson(String produtoJson) {
        this.produtoJson = produtoJson;
    }

    public InputStream getImagemStream() {
        return imagemStream;
    }

    public void setImagemStream(InputStream imagemStream) {
        this.imagemStream = imagemStream;
    }

}
