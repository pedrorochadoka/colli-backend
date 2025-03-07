package br.com.colli.controller;

import br.com.colli.form.ProdutoMultipartForm;
import br.com.colli.model.Produto;
import br.com.colli.service.ProdutoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import java.util.List;
import java.util.Optional;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoController {

    @Inject
    ProdutoService produtoService;

    // Criar um novo produto
    @POST
    public Response criarProduto(Produto produto) {
        System.out.println("### Controller: "+produto.toString());
        Produto novoProduto = produtoService.criarProduto(produto);
        return Response.status(Response.Status.CREATED).entity(novoProduto).build();
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarProdutoComImagem(@MultipartForm ProdutoMultipartForm form) {
        try {
            // Extraindo os dados do DTO
            Produto produtoCriado = produtoService.criarProdutoComImagem(form.getProdutoJson(), form.getImagemStream());
            return Response.status(Response.Status.CREATED).entity(produtoCriado).build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao criar produto com imagem").build();
        }
    }

    // Obter um produto pelo ID
    @GET
    @Path("/{produtoId}")
    public Response obterProdutoPorId(@PathParam("produtoId") Long produtoId) {
        Optional<Produto> produto = produtoService.obterProdutoPorId(produtoId);
        return produto.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)).build();
    }

    // Obter todos os produtos
    @GET
    public Response obterTodosProdutos() {
        try {
            List<Produto> produtos = produtoService.obterTodosProdutos();
            return Response.ok(produtos).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao listar todos os produtos").build();
        }
    }

    // Atualizar um produto
    @PUT
    @Path("/{produtoId}")
    public Response atualizarProduto(@PathParam("produtoId") Long produtoId, Produto produto) {
        Optional<Produto> produtoAtualizado = produtoService.atualizarProduto(produtoId, produto);
        return produtoAtualizado.map(Response::ok)
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)).build();
    }

    // Deletar um produto
    @DELETE
    @Path("/{produtoId}")
    public Response deletarProduto(@PathParam("produtoId") Long produtoId) {
        try {
            boolean deletado = produtoService.deletarProduto(produtoId);
            if (deletado) {
                return Response.status(Response.Status.OK).entity("Produto removido com sucesso.").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Produto não encontrado.").build();
            }
        }catch(Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao excluir o produto.")
                    .build();
        }
    }
}
