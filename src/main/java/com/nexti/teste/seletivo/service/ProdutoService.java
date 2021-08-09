package com.nexti.teste.seletivo.service;

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

import com.nexti.teste.seletivo.controller.ProdutoController;
import com.nexti.teste.seletivo.model.ProdutoModel;

@RequestMapping(value = "/produto")
public class ProdutoService {
	@Autowired
	private ProdutoController controller;

	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<ProdutoModel>> getAllProdutos() {
		return ResponseEntity.ok(controller.findAllProdutos());
	}

	@GetMapping(path = "/id/{idProduto}")
	public ResponseEntity<ProdutoModel> getProdutoById(@PathVariable Long idProduto) {
		return ResponseEntity.of(controller.getProdutoById(idProduto));
	}

	@PutMapping(path = "/id/{idProduto}")
	public ResponseEntity<ProdutoModel> updateProduto(@PathVariable Long idProduto, @RequestBody ProdutoModel produto) {
		ProdutoModel produtoSalvo = controller.updateProduto(idProduto, produto);
		if (produtoSalvo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(produtoSalvo);
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModel> saveProduto(@RequestBody ProdutoModel produto) {
		return ResponseEntity.ok(controller.saveProduto(produto));
	}
	
	@DeleteMapping(path = "/id/{idProduto}")
	public ResponseEntity<Object> deleteProduto(@PathVariable Long idProduto) {
		controller.deleteProduto(idProduto);
		return ResponseEntity.ok(null);
	}
}
