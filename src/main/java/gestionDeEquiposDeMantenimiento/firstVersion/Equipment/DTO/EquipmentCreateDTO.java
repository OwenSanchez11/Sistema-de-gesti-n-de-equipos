
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentCreateDTO {
    private String name;
    private int codeInventory;
    private String description;
    private String brand;
    private String model;
    private int seriesNum;
    private String status;
    private String location;
    private Boolean active; 
}
