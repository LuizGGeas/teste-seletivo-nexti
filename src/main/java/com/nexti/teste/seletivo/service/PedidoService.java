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

import com.nexti.teste.seletivo.controller.PedidoController;
import com.nexti.teste.seletivo.model.PedidoModel;

@RequestMapping(value = "/pedido")
public class PedidoService {
	@Autowired
	private PedidoController controller;

	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<PedidoModel>> getAllPedidos() {
		return ResponseEntity.ok(controller.findAllPedidos());
	}

	@GetMapping(path = "/id/{idPedido}")
	public ResponseEntity<PedidoModel> getPedidoById(@PathVariable Long idPedido) {
		return ResponseEntity.of(controller.findPedidoById(idPedido));
	}

	@PutMapping(path = "/id/{idPedido}")
	public ResponseEntity<PedidoModel> updatePedido(@PathVariable Long idPedido, @RequestBody PedidoModel pedido) {
		PedidoModel pedidoSalvo = controller.updatePedido(idPedido, pedido);
		if (pedidoSalvo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(pedidoSalvo);
	}
	
	@PostMapping
	public ResponseEntity<PedidoModel> savePedidos(@RequestBody PedidoModel pedido) {
		return ResponseEntity.ok(controller.savePedido(pedido));
	}
	
	@DeleteMapping(path = "/id/{idPedido}")
	public ResponseEntity<Object> deletePedido(@PathVariable Long idPedido) {
		controller.deletePedido(idPedido);
		return ResponseEntity.ok(null);
	}
}
