package com.block6_person_controllers.block6_person_controllers.controller;

import com.block6_person_controllers.block6_person_controllers.domain.Ciudad;
import com.block6_person_controllers.block6_person_controllers.service.CiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ciudad1")
public class Ciudad1Controller {

    private final CiudadService ciudadService;

    @Autowired
    public Ciudad1Controller(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @PostMapping(value = "/addCiudad")
    public ResponseEntity<Ciudad> addCiudad(@RequestBody Ciudad ciudad) {
        return new ResponseEntity<>(ciudadService.addCiudad(ciudad), HttpStatus.CREATED);
    }

}
