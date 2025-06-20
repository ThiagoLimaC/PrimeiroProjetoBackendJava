package com.projeto1.primeiro_exemplo.repository;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.projeto1.primeiro_exemplo.model.Produto;

// garante que a classe possa ser usada com injeção de dependência
@Repository
public class ProdutoRepository {

    // Simulando o banco de dados
    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtos
            .stream()
            .filter(produto -> produto.getId() == id)
            .findFirst();
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado
     * @return Retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto){
        
        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Metodo para deletar o produto por Id
     * @param id do produto a ser deletado
     */
    public void deletar(int id){
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto que será atualizado
     * @return o produto após atualizar a lista
     */
    public Produto atualizar(Produto produto){
        
        // encontrar o produto na lista
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());
        
        if (produtoEncontrado.isEmpty()){
            throw new InputMismatchException("Produto não encontrado");
        }

        // remover o produto antigo da lista
        deletar(produto.getId());

        // adicionar o novo produto atualizado na lista
        produtos.add(produto);

        return produto;
    }

}
