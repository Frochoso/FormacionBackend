package block7crudvalidation.block7_crud_validation.service.interfaces;

import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoGet;
import block7crudvalidation.block7_crud_validation.dtos.asignatura.AsignaturaDtoPost;

public interface AsignaturaService {

    public AsignaturaDtoGet addAsignatura(AsignaturaDtoPost asignaturaDtoPost);

    public AsignaturaDtoGet getAsignatura(Integer idAsignatura);

    public AsignaturaDtoGet deleteAsignatura(Integer idAsignatura);

    public AsignaturaDtoGet updateAsignatura(Integer idAsignatura, AsignaturaDtoPost asignaturaDtoPost);

}
