package gestionDeEquiposDeMantenimiento.firstVersion.User;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<UserModel> hasActive(Boolean active) {
        return (root, query, criteriaBuilder) ->{

            if (active == null) {
                return criteriaBuilder.conjunction();
            }

           return criteriaBuilder.equal(root.get("active"),active);
        };



    }

    public static Specification<UserModel> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {

            if (email ==  null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("email"), email);
        };

    }



}
