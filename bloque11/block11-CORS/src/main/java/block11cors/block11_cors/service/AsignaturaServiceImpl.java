package block11cors.block11_cors.service;

import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoGet;
import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoPost;
import block11cors.block11_cors.error.EntityNotFoundException;
import block11cors.block11_cors.error.UnprocessableEntityException;
import block11cors.block11_cors.converters.AsignaturaMapper;
import block11cors.block11_cors.domain.Asignatura;
import block11cors.block11_cors.repository.AsignaturaRepository;
import block11cors.block11_cors.service.interfaces.AsignaturaService;
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
