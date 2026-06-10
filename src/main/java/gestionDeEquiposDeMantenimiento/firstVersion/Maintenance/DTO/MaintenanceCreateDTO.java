
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceCreateDTO {
    private Long idEquipment;
    private Long idUser;
    private String description;
    private String priceMaintenance;
}
