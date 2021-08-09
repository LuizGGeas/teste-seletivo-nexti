package com.nexti.teste.seletivo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = ItemPedidoModel.NM_TABELA)
public class ItemPedidoModel implements Serializable {
	public static final String NM_TABELA = "item_pedido";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_itempedido")
	private Long idItemPedido;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "pedido", referencedColumnName = "id_pedido", nullable = false)
	private PedidoModel pedido;
	
	@JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
	private ProdutoModel produto;
	
	@Column(name = "nr_quantidade")
	private BigDecimal nrQuantidade;
	
	@Column(name = "vl_totalitem")
	private BigDecimal vlTotalItem;

	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public PedidoModel getPedido() {
		return pedido;
	}

	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}

	public ProdutoModel getProduto() {
		return produto;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}

	public BigDecimal getNrQuantidade() {
		return nrQuantidade;
	}

	public void setNrQuantidade(BigDecimal nrQuantidade) {
		this.nrQuantidade = nrQuantidade;
	}

	public BigDecimal getVlTotalItem() {
		return vlTotalItem;
	}

	public void setVlTotalItem(BigDecimal vlTotalItem) {
		this.vlTotalItem = vlTotalItem;
	}
}
