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

import com.nexti.teste.seletivo.controller.ClienteController;
import com.nexti.teste.seletivo.model.ClienteModel;

@RequestMapping(value = "/cliente")
public class ClienteService {

	@Autowired
	private ClienteController controller;

	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<ClienteModel>> getAllClients() {
		return ResponseEntity.ok(controller.getAllClientes());
	}

	@GetMapping(path = "/id/{idCliente}")
	public ResponseEntity<ClienteModel> getClienteById(@PathVariable Long idCliente) {
		return ResponseEntity.of(controller.findClienteById(idCliente));
	}

	@PutMapping(path = "/id/{idCliente}")
	public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long idCliente, @RequestBody ClienteModel cliente) {
		ClienteModel clienteSalvo = controller.updateCliente(idCliente, cliente);
		if (clienteSalvo == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return ResponseEntity.ok(clienteSalvo);
	}
	
	@PostMapping
	public ResponseEntity<ClienteModel> saveCliente(@RequestBody ClienteModel cliente) {
		return ResponseEntity.ok(controller.saveCliente(cliente));
	}
	
	@DeleteMapping(path = "/id/{idCliente}")
	public ResponseEntity<Object> deleteCliente(@PathVariable Long idCliente) {
		controller.deleteCliente(idCliente);
		return ResponseEntity.ok(null);
	}

}
