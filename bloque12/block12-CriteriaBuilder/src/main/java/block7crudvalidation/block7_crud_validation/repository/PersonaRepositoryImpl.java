package block7crudvalidation.block7_crud_validation.repository;

import block7crudvalidation.block7_crud_validation.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PersonaRepositoryImpl {

    @PersistenceContext
    private EntityManager em;


    public List<Persona> findByFilters(HashMap<String, Object> data, Integer pagina, Integer tamanoPagina) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        data.forEach((field, value) -> {
            switch (field) {
                case "usuario":
                    if ((String) value != null) {
                        predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    }
                    break;
                case "name":
                    if ((String) value != null) {
                        predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    }
                    break;
                case "surname":
                    if ((String) value != null) {
                        predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    }
                    break;
                case "fechaHasta":
                    if ((String) value != null) {
                        predicates.add(cb.lessThanOrEqualTo(root.get(field), LocalDate.parse((String) value)));
                    }
                    break;
                case "fechaDesde":
                    if ((String) value != null) {
                        predicates.add(cb.greaterThanOrEqualTo(root.get(field), LocalDate.parse((String) value)));
                    }
                    break;
                case "ordenar":
                    if ((String) value != null) {
                        switch ((String) value) {
                            case "usuario":
                                query.orderBy(cb.asc(root.get("user")));
                                break;
                            case "name":
                                query.orderBy(cb.asc(root.get("name")));
                                break;
                        }
                    }
                    break;
            }

        });

        TypedQuery<Persona> typedQuery = em.createQuery(query);
        typedQuery.setFirstResult((pagina - 1) * tamanoPagina);
        typedQuery.setMaxResults(tamanoPagina);

        return typedQuery.getResultList();
    }

}
