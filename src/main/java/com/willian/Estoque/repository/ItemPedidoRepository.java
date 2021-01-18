package com.willian.Estoque.repository;

import com.willian.Estoque.domain.ItemPedido;
import com.willian.Estoque.domain.ItemPedidoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {
}
