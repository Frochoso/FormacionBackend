package block11cors.block11_cors.controller;

import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoGet;
import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoPost;
import block11cors.block11_cors.service.AsignaturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/asignatura")
public class AsignaturaController {

    @Autowired
    AsignaturaServiceImpl asignaturaService;

    @PostMapping(value = "/addAsignatura")
    public AsignaturaDtoGet addAsignatura(@RequestBody AsignaturaDtoPost asignaturaDtoPost) {
        return asignaturaService.addAsignatura(asignaturaDtoPost);
    }

    @GetMapping(value = "/getAsignatura/{id}")
    public AsignaturaDtoGet getAsignatura(@PathVariable Integer id) {
        return asignaturaService.getAsignatura(id);
    }

    @PutMapping(value = "/updateAsignatura/{id}")
    public AsignaturaDtoGet updateAsignatura(@PathVariable Integer id, @RequestBody AsignaturaDtoPost asignaturaDtoPost) {
        return asignaturaService.updateAsignatura(id, asignaturaDtoPost);
    }

    @DeleteMapping(value = "/deleteAsignatura/{id}")
    public AsignaturaDtoGet deleteAsignatura(@PathVariable Integer id) {
        return asignaturaService.deleteAsignatura(id);
    }

}
