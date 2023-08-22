package block7crudvalidation.block7_crud_validation.controller;

import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDto;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.service.StudentAsignaturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/studentAsignaturas")
public class StudentAsignaturaController {

    @Autowired
    StudentAsignaturaServiceImpl studentAsignaturaService;

    @GetMapping(value = "/getAsignaturaByStudentId/{id}")
    public StudentAsignaturaDtoGet getAsignaturaByStudentId(@PathVariable Integer id) {
        return studentAsignaturaService.findAsignaturaByStudentId(id);
    }

    @DeleteMapping(value = "/removeRelationShips")
    public List<StudentAsignaturaDtoGet> removeAsignaturasFromStudent(@RequestBody List<StudentAsignaturaDto> listaEliminar) {
        return studentAsignaturaService.removeRelationShips(listaEliminar);
    }

    @PostMapping(value = "/addRelationShips")
    public List<StudentAsignaturaDtoGet> addAsignaturasToStudent(@RequestBody List<StudentAsignaturaDto> listaInsertar) {
        return studentAsignaturaService.removeRelationShips(listaInsertar);
    }

}
