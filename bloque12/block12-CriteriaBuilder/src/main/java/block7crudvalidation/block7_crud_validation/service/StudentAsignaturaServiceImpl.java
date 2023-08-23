package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.converters.AsignaturaMapper;
import block7crudvalidation.block7_crud_validation.converters.StudentMapper;
import block7crudvalidation.block7_crud_validation.domain.Asignatura;
import block7crudvalidation.block7_crud_validation.domain.Student;
import block7crudvalidation.block7_crud_validation.domain.StudentAsignaturas;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDto;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignaturaStudent.StudentAsignaturaDtoPost;
import block7crudvalidation.block7_crud_validation.dtos.student.StudentDtoGet;
import block7crudvalidation.block7_crud_validation.repository.interfaces.StudentAsignaturaRepository;
import block7crudvalidation.block7_crud_validation.service.interfaces.AsignaturaService;
import block7crudvalidation.block7_crud_validation.service.interfaces.StudentAsignaturaService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentAsignaturaServiceImpl implements StudentAsignaturaService {

    @Autowired
    StudentAsignaturaRepository studentAsignaturaRepository;

    @Autowired
    AsignaturaService asignaturaService;

    AsignaturaMapper asignaturaMapper = Mappers.getMapper(AsignaturaMapper.class);
    StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

    @Override
    public StudentAsignaturaDtoGet findAsignaturaByStudentId(Integer id) {

        List<AsignaturaDtoGet> listaEliminados = asignaturaMapper
                .asignaturaListToAsignaturaDtoGet(studentAsignaturaRepository.findAsignaturasByStudentId(id));
        StudentDtoGet student = studentMapper.studentToStudentDtoGet(studentAsignaturaRepository.findStudentById(id));

        return new StudentAsignaturaDtoGet(student, listaEliminados);
    }

    @Override
    public void addRelationships(StudentAsignaturaDtoPost studentAsignaturaDtoPost, Student student) {

        Set<StudentAsignaturas> asignaturaList = new HashSet<>();

        for (Integer idAsignatura : studentAsignaturaDtoPost.getIdAsignaturas()) {
            asignaturaList.add(new StudentAsignaturas(null, asignaturaMapper
                    .asignaturaDtoGetToAsignatura(asignaturaService.getAsignatura(idAsignatura))));
        }

        student.setAsignaturas(asignaturaList);
        Asignatura asignaturas;

        for (Integer idAsignatura : studentAsignaturaDtoPost.getIdAsignaturas()) {

            asignaturas = asignaturaMapper.asignaturaDtoGetToAsignatura(asignaturaService.getAsignatura(idAsignatura));
            studentAsignaturaRepository.save(new StudentAsignaturas(student, asignaturas));

        }
    }

    @Override
    public List<StudentAsignaturaDtoGet> removeRelationShips(List<StudentAsignaturaDto> listaEliminar) {
        List<StudentAsignaturaDtoGet> listaEliminados = new ArrayList<>();
        for (StudentAsignaturaDto eliminar : listaEliminar) {
            Student student = studentAsignaturaRepository.findStudentById(eliminar.getIdStudent());
            List<Asignatura> asignaturas = studentAsignaturaRepository.
                    findAsignaturasByStudentId(eliminar.getIdStudent());

            for (Asignatura asignaturaEncontrada : asignaturas) {
                eliminar.getAsignaturas().stream()
                        .filter(x -> x.equals(asignaturaEncontrada.getIdAsignatura()))
                        .forEach(filteredAsignatura -> {
                            studentAsignaturaRepository.delete(new StudentAsignaturas(student, asignaturaEncontrada));
                        });
                listaEliminados.add(new StudentAsignaturaDtoGet(studentMapper.studentToStudentDtoGet(student),
                        List.of(asignaturaMapper.asignaturaToAsignaturaDtoGet(asignaturaEncontrada))));
            }

        }

        return listaEliminados;
    }

    @Override
    public List<StudentAsignaturaDtoGet> addRelationShips(List<StudentAsignaturaDto> listaInsertar) {
        List<StudentAsignaturaDtoGet> listaInsertados = new ArrayList<>();
        for (StudentAsignaturaDto insertar : listaInsertar) {
            Student student = studentAsignaturaRepository.findStudentById(insertar.getIdStudent());
            List<Asignatura> asignaturas = studentAsignaturaRepository.
                    findAsignaturasByStudentId(insertar.getIdStudent());

            for (Asignatura asignaturaEncontrada : asignaturas) {
                insertar.getAsignaturas().stream()
                        .filter(x -> x.equals(asignaturaEncontrada.getIdAsignatura()))
                        .forEach(filteredAsignatura -> {
                            studentAsignaturaRepository.save(new StudentAsignaturas(student, asignaturaEncontrada));
                        });
                listaInsertados.add(new StudentAsignaturaDtoGet(studentMapper.studentToStudentDtoGet(student),
                        List.of(asignaturaMapper.asignaturaToAsignaturaDtoGet(asignaturaEncontrada))));
            }
        }
        return listaInsertados;
    }

}
