package com.nexti.teste.seletivo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nexti.teste.seletivo.model.ClienteModel;
import com.nexti.teste.seletivo.repository.ClienteRepository;

@RestController
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	public Iterable<ClienteModel> getAllClientes() {
		return repository.findAll();
	}
	
	public Optional<ClienteModel> findClienteById(Long idCliente) {
		return repository.findById(idCliente);
	}
	
	public ClienteModel updateCliente(Long idCliente, ClienteModel clienteSalvar) {
		Optional<ClienteModel> clienteSalvo = repository.findById(idCliente);
		
		if (clienteSalvo.isPresent()) {
			ClienteModel cliente = clienteSalvo.get();
			repository.save(cliente);
			return cliente;
		}
		
		return null;
	}
	
	public ClienteModel saveCliente(ClienteModel cliente) {
		return repository.save(cliente);
	}
	
	public void deleteCliente(Long idCliente) {
		repository.deleteById(idCliente);
	}

}
