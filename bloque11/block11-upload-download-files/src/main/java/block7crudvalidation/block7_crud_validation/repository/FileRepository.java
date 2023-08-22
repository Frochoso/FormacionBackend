package block7crudvalidation.block7_crud_validation.repository;

import block7crudvalidation.block7_crud_validation.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

    File findByName(String name);

}
