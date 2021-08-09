package com.nexti.teste.seletivo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = ProdutoModel.NM_TABELA)
public class ProdutoModel implements Serializable{
	public static final String NM_TABELA = "produto";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long idProduto;
	
	@Column(name = "nm_produto")
	private String nmProduto;
	
	@Column(name = "desc_produto")
	private String dsProduto;
	
	@Column(name = "vl_produto")
	private BigDecimal vlProduto;
	
	@Column(name = "nr_qtdeestoque")
	private BigDecimal nrQtdeEstoque;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public BigDecimal getVlProduto() {
		return vlProduto;
	}

	public void setVlProduto(BigDecimal vlProduto) {
		this.vlProduto = vlProduto;
	}

	public BigDecimal getNrQtdeEstoque() {
		return nrQtdeEstoque;
	}

	public void setNrQtdeEstoque(BigDecimal nrQtdeEstoque) {
		this.nrQtdeEstoque = nrQtdeEstoque;
	}
}
