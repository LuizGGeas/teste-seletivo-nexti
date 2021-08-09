package com.nexti.teste.seletivo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexti.teste.seletivo.model.ProdutoModel;
import com.nexti.teste.seletivo.repository.ProdutoRepository;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<ProdutoModel>> getAllProdutos() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping(path = "/id/{idProduto}")
	public ResponseEntity<ProdutoModel> getProdutoById(@PathVariable Long idProduto) {
		return ResponseEntity.of(repository.findById(idProduto));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> saveProduto(@RequestBody ProdutoModel produto) {
		return ResponseEntity.ok(repository.save(produto));
	}
	
	@DeleteMapping(path = "/id/{idProduto}")
	public ResponseEntity<Object> deleteProduto(@PathVariable Long idProduto) {
		repository.deleteById(idProduto);
		return ResponseEntity.ok(null);
	}
	
	@PutMapping(path = "/id/{idProduto}")
	public ResponseEntity<ProdutoModel> updateProduto(@PathVariable Long idProduto, @RequestBody ProdutoModel produtoAtualizado) {
		Optional<ProdutoModel> produtoSalvo = repository.findById(idProduto);
		
		if (produtoSalvo.isPresent()) {
			ProdutoModel produto = produtoSalvo.get();
			
			produto.setDsProduto(produtoAtualizado.getDsProduto());
			produto.setIdProduto(produtoAtualizado.getIdProduto());
			produto.setNmProduto(produtoAtualizado.getNmProduto());
			produto.setNrQtdeEstoque(produtoAtualizado.getNrQtdeEstoque());
			produto.setVlProduto(produtoAtualizado.getVlProduto());
			
			repository.save(produto);
			
			return ResponseEntity.ok(produto);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
