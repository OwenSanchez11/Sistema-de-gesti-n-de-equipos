package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance;

import org.springframework.data.jpa.domain.Specification;

public class MaintenanceSpecification {

    public static Specification<MaintenanceModel> hasEquipmentId(Long idEquipment) {

        if (idEquipment == null) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->

            criteriaBuilder.equal(root.get("equipment").get("idEquipment"), idEquipment);

    }

}
