package com.nexti.teste.seletivo.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

@Entity
@Table(name = PedidoModel.NM_TABELA)
public class PedidoModel implements Serializable{
	public final static String NM_TABELA = "pedido";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long idPedido;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private ClienteModel cliente;
	
	@Column(name = "vl_totalpedido")
	private BigDecimal vlTotalPedido;
	
	@JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "dt_pedido")
	private LocalDate dtPedido;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "pedido", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<ItemPedidoModel> itemPedidoList;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public ClienteModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getVlTotalPedido() {
		return vlTotalPedido;
	}

	public void setVlTotalPedido(BigDecimal vlTotalPedido) {
		this.vlTotalPedido = vlTotalPedido;
	}

	public LocalDate getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(LocalDate dtPedido) {
		this.dtPedido = dtPedido;
	}

	public List<ItemPedidoModel> getItemPedidoModelList() {
		return itemPedidoList;
	}

	public void setItemPedidoModelList(List<ItemPedidoModel> itemPedidoModelList) {
		this.itemPedidoList = itemPedidoModelList;
	}
}
