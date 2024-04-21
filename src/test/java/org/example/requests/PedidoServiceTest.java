package org.example.requests;

import com.softexpert.projetotesteapi.model.Cliente;
import com.softexpert.projetotesteapi.model.Pedido;
import com.softexpert.projetotesteapi.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    private PedidoService service;

    @BeforeEach
    void setUp() {
        service = new PedidoService();
    }

    @Test
    void criarEntidade() {
        Pedido pedido = new Pedido();
        pedido.setDesconto(BigDecimal.valueOf(10));
        pedido.setTipoDesconto("%");
        pedido.setValorTotal(BigDecimal.valueOf(100));

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setValorAPagar(BigDecimal.valueOf(100));

        List<Cliente> clientes = new ArrayList<>();
        clientes.add(cliente);

        pedido.setClientes(clientes);

        Pedido createdPedido = service.criarEntidade(pedido);

        assertNotNull(createdPedido.getId());
        assertEquals(100, createdPedido.getValorTotal());
        assertEquals(10, createdPedido.getDesconto());
        assertEquals("%", createdPedido.getTipoDesconto());

        Cliente createdCliente = createdPedido.getClientes().get(0);
        assertEquals(2, createdCliente.getId());
        assertEquals(90, createdCliente.getValorAPagar());
    }

    @Test
    void getByID() {
        Pedido pedido = new Pedido();
        pedido.setDesconto(BigDecimal.valueOf(10));
        pedido.setTipoDesconto("%");
        pedido.setValorTotal(BigDecimal.valueOf(100));

        when(service.getByID(1L)).thenReturn(pedido);
        Pedido resultado = service.getByID(1L);
        assertEquals(pedido, resultado);
    }

}