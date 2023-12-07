package com.softexpert.projetotesteapi.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Pedido {

    private Long id;
    private BigDecimal valorEntrega;
    private BigDecimal desconto;
    private String tipoDesconto;
    private BigDecimal valorTotal;
    private List<Cliente> clientes;

    public BigDecimal calcularValorTotal() {
        BigDecimal valorTotalClientes = BigDecimal.ZERO;
        for (Cliente cliente : Optional.ofNullable(clientes).orElse(List.of())) {
            valorTotalClientes = valorTotalClientes.add(cliente.calcularValorPedido());
        }
        return valorTotalClientes.add(valorEntrega);
    }
}
