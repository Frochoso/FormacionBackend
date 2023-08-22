package block11cors.block11_cors.service.interfaces;

import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoGet;
import block11cors.block11_cors.dtos.asignatura.AsignaturaDtoPost;

public interface AsignaturaService {

    public AsignaturaDtoGet addAsignatura(AsignaturaDtoPost asignaturaDtoPost);

    public AsignaturaDtoGet getAsignatura(Integer idAsignatura);

    public AsignaturaDtoGet deleteAsignatura(Integer idAsignatura);

    public AsignaturaDtoGet updateAsignatura(Integer idAsignatura, AsignaturaDtoPost asignaturaDtoPost);

}
