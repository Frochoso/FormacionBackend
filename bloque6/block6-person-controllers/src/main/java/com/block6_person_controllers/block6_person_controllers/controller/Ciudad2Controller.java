package com.block6_person_controllers.block6_person_controllers.controller;

import com.block6_person_controllers.block6_person_controllers.domain.Ciudad;
import com.block6_person_controllers.block6_person_controllers.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ciudad2")
public class Ciudad2Controller {

    private CiudadService ciudadService;

    @Autowired
    public Ciudad2Controller(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @GetMapping
    public List<Ciudad> getCiudad() {
        return ciudadService.getCiudades();
    }

}
