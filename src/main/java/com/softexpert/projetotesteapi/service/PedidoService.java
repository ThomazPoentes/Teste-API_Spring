package com.softexpert.projetotesteapi.service;

import com.softexpert.projetotesteapi.model.Cliente;
import com.softexpert.projetotesteapi.model.Pedido;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PedidoService {

    private static final Map<Long, Pedido> entityDTO = new HashMap<>();

    public Pedido criarEntidade(Pedido entity) {
        Long generateID = entityDTO.size() + 1L;
        entity.setId(generateID);
        entityDTO.put(generateID, entity);
        normalizeEntity(entity);
        return entity;
    }

    public Pedido normalizeEntity(Pedido entity) {
        entity.calcularValorTotal();
        calculoComDesconto(entity);
        normalizeCliente(entity);
        return entity;
    }

    public Pedido getByID(Long id) {
        return entityDTO.get(id);
    }

    public List<Pedido> getAll() {
        return new ArrayList<>(entityDTO.values());
    }

    public String delete(Long id) {
        entityDTO.remove(id);
        return "DELETED".concat(String.valueOf(id));
    }

    public BigDecimal calculoComDesconto(Pedido entity) {
        BigDecimal valorComDesconto = entity.calcularValorTotal();
        if ("%".equals(entity.getTipoDesconto()))
            return valorComDesconto.subtract(entity.getDesconto().divide(BigDecimal.valueOf(100)).multiply(entity.getValorTotal()));
        else
            return valorComDesconto.subtract(entity.getDesconto());
    }

    public void normalizeCliente(Pedido entity) {
        for (Cliente cliente : entity.getClientes()) {
            cliente.setValorAPagar(calculoComDesconto(entity));
            cliente.setId(cliente.getId() + 1);
        }
    }

}