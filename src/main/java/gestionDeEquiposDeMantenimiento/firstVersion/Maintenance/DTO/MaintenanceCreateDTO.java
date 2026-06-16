
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaintenanceCreateDTO {
    @NotNull(message = "id Equipment is required")
    private Long idEquipment;
    @NotNull(message = "user id is required")
    private Long idUser;
    @NotBlank(message = "Description cannot be empty")
    private String description;
    @NotBlank(message = "Price Maintenance cannot by empty")
    private String priceMaintenance;
}
