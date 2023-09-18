package com.example.backendfrontend.feign;

import com.example.backendfrontend.dto.cliente.ClienteDtoGet;
import com.example.backendfrontend.dto.viaje.ViajeDtoGet;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "backend:8080", name = "ticketFeign")
public interface Feign {

    @GetMapping("/passenger/findCliente/{idCliente}")
    ClienteDtoGet findCliente(@PathVariable int idCliente) throws EntityNotFoundException;

    @GetMapping("/trip/findViaje/{idViaje}")
    ViajeDtoGet findViaje(@PathVariable int idViaje) throws EntityNotFoundException;

}
