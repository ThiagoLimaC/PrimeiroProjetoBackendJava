package com.projeto1.primeiro_exemplo.services;

import java.lang.classfile.ClassFile.Option;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.projeto1.primeiro_exemplo.model.Produto;
import com.projeto1.primeiro_exemplo.model.exception.ResourceNotFoundException;
import com.projeto1.primeiro_exemplo.repository.ProdutoRepository;
import com.projeto1.primeiro_exemplo.repository.ProdutoRepository_old;
import com.projeto1.primeiro_exemplo.shared.ProdutoDTO;

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
    public List<ProdutoDTO> obterTodos(){
        
        // Retorna uma lista de produtos
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
            .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
            .collect(Collectors.toList());
    }


    /**
     * Metodo que retorna o produto encontrado pelo seu Id
     * @param id do produto que será localizado
     * @return Retorna um produto caso seja encontrado
     */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        
        // Obtendo optional de produto por id
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não encontrar lança exception
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        // Convertendo o meu optional de produto em um produtoDto
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        return Optional.of(dto);
    }

     /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado
     * @return Retorna o produto que foi adicionado na lista
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
       // poderia ter alguma regra de negócio para validar o produto.
       produtoDto.setId(null); 

       // Criar um objeto de mapeamento
       ModelMapper mapper = new ModelMapper();

       // Converter o nosso ProdutoDto em um Produto
       Produto produto = mapper.map(produtoDto, Produto.class);

       // Salvar o produto no banco
       produto = produtoRepository.save(produto);

       produtoDto.setId(produto.getId());

       // Retornar o ProdutoDto atualizado

       return produtoDto;
    }

    /**
     * Metodo para deletar o produto por Id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){

        // Verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não existir lança uma exception
        if (produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto que será atualizado
     * @param id do produto
     * @return o produto após atualizar a lista
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        
        // Passar o id para o produtoDto
        produtoDto.setId(id);

        // Criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // Converter o DTO em um produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Atualizar o produto no Banco de Dados
        produtoRepository.save(produto);

        // Retornar o produtoDto atualizado
        return produtoDto;
    }

}
