package com.nexti.teste.seletivo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.nexti.teste.seletivo.model.ItemPedidoModel;
import com.nexti.teste.seletivo.model.PedidoModel;
import com.nexti.teste.seletivo.repository.ItemPedidoRepository;
import com.nexti.teste.seletivo.repository.PedidoRepository;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<PedidoModel>> getAllPedidos() {
		return ResponseEntity.ok(pedidoRepository.findAll());
	}

	@GetMapping(path = "/id/{idPedido}")
	public ResponseEntity<PedidoModel> getPedidoById(@PathVariable Long idPedido) {
		return ResponseEntity.of(pedidoRepository.findById(idPedido));
	}

	@PostMapping
	public ResponseEntity<PedidoModel> savePedidos(@RequestBody PedidoModel pedido) {
		return ResponseEntity.ok(pedidoRepository.save(pedido));
	}

	@DeleteMapping(path = "/id/{idPedido}")
	public ResponseEntity<Object> deletePedido(@PathVariable Long idPedido) {
		pedidoRepository.deleteById(idPedido);
		return ResponseEntity.ok(null);
	}

	@PutMapping(path = "/id/{idPedido}")
	public ResponseEntity<PedidoModel> updatePedido(@PathVariable Long idPedido,
			@RequestBody PedidoModel pedidoAtualizado) {
		final Optional<PedidoModel> pedidoSalvo = pedidoRepository.findById(idPedido);

		if (pedidoSalvo.isPresent()) {
			final PedidoModel pedido = pedidoSalvo.get();
			pedido.setCliente(pedidoAtualizado.getCliente());
			pedido.setDtPedido(pedidoAtualizado.getDtPedido());
			pedido.setIdPedido(pedidoAtualizado.getIdPedido());
			pedido.setItemPedidoModelList(pedidoAtualizado.getItemPedidoModelList());
			pedido.setVlTotalPedido(pedidoAtualizado.getVlTotalPedido());

			pedidoRepository.save(pedido);
			return ResponseEntity.ok(pedido);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(path = "/pedido/produto/{idPedido}/{idProduto}")
	public ResponseEntity<PedidoModel> dropItemPedidoByIdProduto(@PathVariable Long idPedido,
			@PathVariable Long idProduto) {
		Optional<PedidoModel> pedidoRealizado = pedidoRepository.findById(idPedido);

		if (pedidoRealizado.isPresent()) {
			PedidoModel pedido = pedidoRealizado.get();
			List<Long> itemPedidoParaRemocaoList = pedido.getItemPedidoModelList().stream()
					.filter(item -> item.getProduto().getIdProduto().equals(idProduto))
					.map(ItemPedidoModel::getIdItemPedido).collect(Collectors.toList());

			itemPedidoRepository.deleteAllById(itemPedidoParaRemocaoList);
			return ResponseEntity.ok(pedidoRepository.getById(idPedido));
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(path = "/cliente-pedido/{idPedido}")
	public ResponseEntity<PedidoModel> removerClienteFromPedido(@PathVariable Long idPedido) {
		Optional<PedidoModel> pedidoRealizado = pedidoRepository.findById(idPedido);
		if (pedidoRealizado.isPresent()) {
			PedidoModel pedido = pedidoRealizado.get();
			pedido.setCliente(null);

			return ResponseEntity.ok(pedidoRepository.save(pedido));
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
