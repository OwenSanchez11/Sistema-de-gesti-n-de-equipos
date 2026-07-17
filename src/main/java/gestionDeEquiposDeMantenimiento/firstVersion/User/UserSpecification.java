package gestionDeEquiposDeMantenimiento.firstVersion.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserModel> hasActive(Boolean active) {

        if (active == null) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(root.get("active"),active);


    }

    public static Specification<UserModel> hasEmail(String email) {

        if (email ==  null) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->

            criteriaBuilder.equal(root.get("email"), email);


    }



}
