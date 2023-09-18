package com.example.backend.controller;


import com.example.backend.dto.viaje.ViajeDtoGet;
import com.example.backend.dto.viaje.ViajeDtoPost;
import com.example.backend.service.interfaces.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trip")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @GetMapping("/findViaje/{idViaje}")
    public ViajeDtoGet findViaje(@PathVariable int idViaje) {
        return viajeService.getViaje(idViaje);
    }

    @PostMapping("/addViaje")
    public ViajeDtoGet addViaje(@RequestBody ViajeDtoPost viajeDtoPost) {
        return viajeService.addViaje(viajeDtoPost);
    }

    @DeleteMapping("/deleteViaje/{idViaje}")
    public ViajeDtoGet removeViaje(@PathVariable int idViaje) {
        return viajeService.removeViaje(idViaje);
    }

    @PutMapping("/updateViaje/{idViaje}")
    public ViajeDtoGet updateViaje(@PathVariable int idViaje, @RequestBody ViajeDtoPost viajeDtoPost) {
        return viajeService.updateViaje(idViaje, viajeDtoPost);
    }

    @PostMapping("/addPassenger/{idViaje}/{idCliente}")
    public ViajeDtoGet addPassenger(@PathVariable int idViaje, @PathVariable int idCliente) {
        return viajeService.addPassenger(idViaje, idCliente);
    }

    @PutMapping("/{idViaje}/{tripStatus}")
    public ViajeDtoGet updateTripStatus(@PathVariable int idViaje, @RequestBody ViajeDtoPost viajeDtoPost) {
        return viajeService.updateViaje(idViaje, viajeDtoPost);
    }

    @GetMapping("/verify/{idViaje}")
    public int checkTripStatus(@PathVariable int idViaje) {
        return viajeService.tripStatus(idViaje);
    }
}
