package com.nexti.teste.seletivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexti.teste.seletivo.model.PedidoModel;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Long>{
}
