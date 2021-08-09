package com.nexti.teste.seletivo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nexti.teste.seletivo.model.ProdutoModel;
import com.nexti.teste.seletivo.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	@Autowired
	private ProdutoRepository repository;
	
	public List<ProdutoModel> findAllProdutos() {
		return repository.findAll();
	}
	
	public Optional<ProdutoModel> getProdutoById(Long idProduto) {
		return repository.findById(idProduto);
	}
	
	public ProdutoModel saveProduto(ProdutoModel produto) {
		return repository.save(produto);
	}
	
	public void deleteProduto(Long idProduto) {
		repository.deleteById(idProduto);
	}
	
	public ProdutoModel updateProduto(Long idProduto, ProdutoModel produtoAtualizado) {
		Optional<ProdutoModel> produtoSalvo = repository.findById(idProduto);
		
		if (produtoSalvo.isPresent()) {
			ProdutoModel produto = produtoSalvo.get();
			
			produto.setDsProduto(produtoAtualizado.getDsProduto());
			produto.setIdProduto(produtoAtualizado.getIdProduto());
			produto.setNmProduto(produtoAtualizado.getNmProduto());
			produto.setNrQtdeEstoque(produtoAtualizado.getNrQtdeEstoque());
			produto.setVlProduto(produtoAtualizado.getVlProduto());
			
			repository.save(produto);
			
			return produto;
		}
		
		return null;
	}
}
