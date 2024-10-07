package com.pe.unmsm.fisi.alfashop.infrastructure.repository;

import com.pe.unmsm.fisi.alfashop.model.Pedido;
import com.pe.unmsm.fisi.alfashop.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findPedidoByUsuario(Usuario usuario);
}
