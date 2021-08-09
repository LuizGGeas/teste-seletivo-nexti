package com.nexti.teste.seletivo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nexti.teste.seletivo.model.ItemPedidoModel;
import com.nexti.teste.seletivo.model.PedidoModel;
import com.nexti.teste.seletivo.repository.ItemPedidoRepository;
import com.nexti.teste.seletivo.repository.PedidoRepository;

@RestController
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public List<PedidoModel> findAllPedidos() {
		return pedidoRepository.findAll();
	}

	public Optional<PedidoModel> findPedidoById(Long idPedido) {
		return pedidoRepository.findById(idPedido);
	}

	public PedidoModel savePedido(PedidoModel pedido) {
		return pedidoRepository.save(pedido);
	}

	public void deletePedido(Long idPedido) {
		pedidoRepository.deleteById(idPedido);
	}

	public PedidoModel updatePedido(Long idPedido, PedidoModel pedidoAtualizado) {
		final Optional<PedidoModel> pedidoSalvo = pedidoRepository.findById(idPedido);

		if (pedidoSalvo.isPresent()) {
			final PedidoModel pedido = pedidoSalvo.get();
			pedido.setCliente(pedidoAtualizado.getCliente());
			pedido.setDtPedido(pedidoAtualizado.getDtPedido());
			pedido.setIdPedido(pedidoAtualizado.getIdPedido());
			pedido.setItemPedidoModelList(pedidoAtualizado.getItemPedidoModelList());
			pedido.setVlTotalPedido(pedidoAtualizado.getVlTotalPedido());

			pedidoRepository.save(pedido);
			return pedido;
		}

		return null;
	}

	public PedidoModel dropItemPedidoByIdProduto(Long idPedido, Long idProduto) {
		Optional<PedidoModel> pedidoRealizado = pedidoRepository.findById(idPedido);

		if (pedidoRealizado.isPresent()) {
			PedidoModel pedido = pedidoRealizado.get();
			List<Long> itemPedidoParaRemocaoList = pedido.getItemPedidoModelList().stream()
					.filter(item -> item.getProduto().getIdProduto().equals(idProduto))
					.map(ItemPedidoModel::getIdItemPedido).collect(Collectors.toList());

			itemPedidoRepository.deleteAllById(itemPedidoParaRemocaoList);

			return pedidoRepository.getById(idPedido);
		}

		return null;
	}

	public PedidoModel removerClienteFromPedido(Long idPedido, Long idCliente) {
		Optional<PedidoModel> pedidoRealizado = pedidoRepository.findById(idPedido);
		if (pedidoRealizado.isPresent()) {
			PedidoModel pedido = pedidoRealizado.get();
			pedido.setCliente(null);
			
			return pedidoRepository.save(pedido);
		}

		return null;
	}
}
