package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import org.springframework.data.jpa.domain.Specification;

public class EquipmentSpecification {

    public static Specification<EquipmentModel> hasEquipmentName(String name) {

        if (name == null || name.isBlank()) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->

                criteriaBuilder.like(root.get("name"), name);


    }


    public static Specification<EquipmentModel> hasEquipmentSeriesNum(Integer seriesNum) {
        if (seriesNum == null) {
            return Specification.unrestricted();
        }

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("seriesNum"), seriesNum);


    }

}
