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

import com.nexti.teste.seletivo.model.ClienteModel;
import com.nexti.teste.seletivo.repository.ClienteRepository;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;

	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<ClienteModel>> getAllClients() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping(path = "/id/{idCliente}")
	public ResponseEntity<ClienteModel> getClienteById(@PathVariable Long idCliente) {
		return ResponseEntity.of(repository.findById(idCliente));
	}

	@PutMapping(path = "/id/{idCliente}")
	public ResponseEntity<ClienteModel> updateCliente(@PathVariable Long idCliente, @RequestBody ClienteModel clienteAtualizado) {
		Optional<ClienteModel> clienteSalvo = repository.findById(idCliente);

		if (clienteSalvo.isPresent()) {
			ClienteModel cliente = clienteSalvo.get();
			cliente.setDtNascimento(clienteAtualizado.getDtNascimento());
			cliente.setIdCliente(clienteAtualizado.getIdCliente());
			cliente.setNmCliente(clienteAtualizado.getNmCliente());
			cliente.setNrCPF(clienteAtualizado.getNrCPF());
			repository.save(cliente);
			return ResponseEntity.ok(cliente);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<ClienteModel> saveCliente(@RequestBody ClienteModel cliente) {
		return ResponseEntity.ok(repository.save(cliente));
	}

	@DeleteMapping(path = "/id/{idCliente}")
	public ResponseEntity<Object> deleteCliente(@PathVariable Long idCliente) {
		repository.deleteById(idCliente);
		return ResponseEntity.ok(null);
	}

}
