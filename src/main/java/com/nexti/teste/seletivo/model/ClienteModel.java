package com.nexti.teste.seletivo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = ClienteModel.NM_TABELA)
public class ClienteModel implements Serializable{
	public static final String NM_TABELA = "cliente";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name = "nm_cliente")
	private String nmCliente;
	
	@Column(name = "nr_documento")
	private String nrCPF;
	
	@Column(name = "dt_nascimento")
	private LocalDate dtNascimento;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNmCliente() {
		return nmCliente;
	}

	public void setNmCliente(String nmCliente) {
		this.nmCliente = nmCliente;
	}

	public String getNrCPF() {
		return nrCPF;
	}

	public void setNrCPF(String nrCPF) {
		this.nrCPF = nrCPF;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
}
