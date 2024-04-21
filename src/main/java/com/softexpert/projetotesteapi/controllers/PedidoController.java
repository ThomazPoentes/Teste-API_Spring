package com.softexpert.projetotesteapi.controllers;

import com.mercadopago.resources.customer.Customer;
import com.softexpert.projetotesteapi.model.Pedido;
import com.softexpert.projetotesteapi.service.MercadoPagoService;
import com.softexpert.projetotesteapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/process_payment")
    public String processPayment(Customer customer) {
        return mercadoPagoService.sendBoleto(customer);
    }

    @PostMapping
    public Pedido create(@RequestBody Pedido entity) {
        return service.criarEntidade(entity);
    }

    @PutMapping("/{id}")
    public Pedido normalize(@PathVariable("id") Long id, @RequestBody Pedido entity) {
        return service.normalizeEntity(entity);
    }

    @GetMapping
    public List<Pedido> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Pedido findById(@PathVariable("id") Long id) {
        return service.getByID(id);
    }

    @DeleteMapping
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }

}