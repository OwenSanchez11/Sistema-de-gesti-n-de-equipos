
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Formato que recibe la DB para crear un nuevo mantenimiento de equipo")
public class MaintenanceCreateDTO {
    
    @Schema(description = "id que se debe ingresar sobre el equipo el cual se le realizará mantenimiento", example = "1, 2, 3")
    @NotNull(message = "id Equipment is required")
    private Long idEquipment;
    
    @Schema(description = "id que se debe ingresar sobre el usuario que lo ingresó a mantenimiento", example = "1, 2, 3")
    @NotNull(message = "user id is required")
    private Long idUser;
    
    @Schema(description = "descripción por el cual el equipo fue ingresado a mantenimiento")
    @NotBlank(message = "Description cannot be empty")
    private String description;
    
    @Schema(description = "precio del mantenimiento")
    @NotBlank(message = "Price Maintenance cannot by empty")
    private String priceMaintenance;
}
