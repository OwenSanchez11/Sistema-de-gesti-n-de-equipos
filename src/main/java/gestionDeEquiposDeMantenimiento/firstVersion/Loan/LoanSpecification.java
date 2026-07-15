package gestionDeEquiposDeMantenimiento.firstVersion.Loan;

import org.springframework.data.jpa.domain.Specification;

public class LoanSpecification {

    public static Specification<LoanModel> hasIdEquipment(Long idEquipment) {

        if (idEquipment == null) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("equipment").get("idEquipment"), idEquipment);
    }


}
