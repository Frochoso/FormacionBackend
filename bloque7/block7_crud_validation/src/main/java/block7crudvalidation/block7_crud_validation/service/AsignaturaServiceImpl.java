package block7crudvalidation.block7_crud_validation.service;

import block7crudvalidation.block7_crud_validation.converters.AsignaturaMapper;
import block7crudvalidation.block7_crud_validation.domain.Asignatura;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoPost;
import block7crudvalidation.block7_crud_validation.error.EntityNotFoundException;
import block7crudvalidation.block7_crud_validation.error.UnprocessableEntityException;
import block7crudvalidation.block7_crud_validation.repository.AsignaturaRepository;
import block7crudvalidation.block7_crud_validation.service.interfaces.AsignaturaService;
import block7crudvalidation.block7_crud_validation.service.interfaces.StudentAsignaturaService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServiceImpl implements AsignaturaService {

    @Autowired
    AsignaturaRepository asignaturaRepository;

    AsignaturaMapper mapper = Mappers.getMapper(AsignaturaMapper.class);

    @Override
    public AsignaturaDtoGet addAsignatura(AsignaturaDtoPost asignaturaDtoPost) {
        return mapper.asignaturaToAsignaturaDtoGet(asignaturaRepository.save(mapper.asignaturaDtoPostToAsignatura(asignaturaDtoPost)));
    }

    @Override
    public AsignaturaDtoGet getAsignatura(Integer idAsignatura) {
        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() -> new EntityNotFoundException("Asignatura con id: " + idAsignatura + " no encontrada."));
        return mapper.asignaturaToAsignaturaDtoGet(asignatura);
    }

    @Override
    public AsignaturaDtoGet deleteAsignatura(Integer idAsignatura) {
        if (asignaturaRepository.findAsignaturas(idAsignatura) != null) {
            throw new UnprocessableEntityException("No se puede borrar la asignatura con id: " + idAsignatura + " porque tiene estudiantes asociados a ella.");
        }
        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() -> new EntityNotFoundException("Asignatura con id: " + idAsignatura + " no encontrada."));
        asignaturaRepository.delete(asignatura);
        return mapper.asignaturaToAsignaturaDtoGet(asignatura);
    }

    @Override
    public AsignaturaDtoGet updateAsignatura(Integer idAsignatura, AsignaturaDtoPost asignaturaDtoPost) {

        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(() -> new EntityNotFoundException("Asignatura con id: " + idAsignatura + " no encontrada."));

        asignatura.setNombreAsignatura(asignaturaDtoPost.getNombreAsignatura());
        asignatura.setComments(asignaturaDtoPost.getComments());
        asignatura.setInitialDate(asignaturaDtoPost.getInitialDate());
        asignatura.setFinishDate(asignaturaDtoPost.getFinishDate());

        return mapper.asignaturaToAsignaturaDtoGet(asignatura);
    }
}
