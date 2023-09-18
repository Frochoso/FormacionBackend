package com.example.backend.controller;

import com.example.backend.dto.cliente.ClienteDtoGet;
import com.example.backend.dto.cliente.ClienteDtoPost;
import com.example.backend.service.interfaces.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/passenger")
public class ClienteController {

    @Autowired
    ClienteService clienteService;


    @GetMapping("/findCliente/{idCliente}")
    public ClienteDtoGet findCliente(@PathVariable int idCliente) {
        return clienteService.findCliente(idCliente);
    }

    @PostMapping("/addCliente")
    public ClienteDtoGet addCliente(@RequestBody ClienteDtoPost clienteDtoPost) {
        return clienteService.addCliente(clienteDtoPost);
    }

    @DeleteMapping("/deleteCliente/{idCliente}")
    public ClienteDtoGet removeCliente(@PathVariable int idCliente) {
        return clienteService.removeCliente(idCliente);
    }

    @PutMapping("/updateCliente/{idCliente}")
    public ClienteDtoGet updateCliente(@PathVariable int idCliente, @RequestBody ClienteDtoPost clienteDtoPost) {
        return clienteService.updateCliente(idCliente, clienteDtoPost);
    }

    @GetMapping("/count/{idViaje}")
    public int countPassengers(@PathVariable int idViaje) {
        return clienteService.countPassengers(idViaje);
    }

}
