package gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EquipmentResponseDTO {
    private Long idEquipment;
    private int codeInventory;
    private String name;
    private String description;
    private String brand;
    private String model;
    private int seriesNum;
    private String status;
    private String location;
    private Boolean active;
}
