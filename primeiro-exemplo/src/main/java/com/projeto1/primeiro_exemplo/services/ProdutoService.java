package com.projeto1.primeiro_exemplo.services;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto1.primeiro_exemplo.model.Produto;
import com.projeto1.primeiro_exemplo.repository.ProdutoRepository;

// define que a classe implementa o repository
@Service
public class ProdutoService {
    
    // intância pronta da injeção de depedência
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos.
     */
    public List<Produto> obterTodos(){
        // regra caso tenha
        return produtoRepository.obterTodos();
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu Id
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<Produto> obterPorId(Integer id){
        return produtoRepository.obterPorId(id);
    }

     /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado
     * @return Retorna o produto que foi adicionado na lista
     */
    public Produto adicionar(Produto produto){
       // poderia ter alguma regra de negócio para validar o produto.
        return produtoRepository.adicionar(produto);
    }

    /**
     * Metodo para deletar o produto por Id
     * @param id do produto a ser deletado
     */
    public void deletar(int id){
        produtoRepository.deletar(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto que será atualizado
     * @param id do produto
     * @return o produto após atualizar a lista
     */
    public Produto atualizar(Integer id, Produto produto){
        // Ter alguma validação
        produto.setId(id);

        return produtoRepository.atualizar(produto);
    }

}
