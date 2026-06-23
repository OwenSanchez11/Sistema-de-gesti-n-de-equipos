
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;


@Schema(name= "LoanCreateDTO" , description  ="Respuesta que se le da al usuario cuando quiere revisar un mantenimiento")
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MaintenanceResponseDTO(
        @Schema(description = "mostrará el id del mantenimiento", example = "1, 2, 3")
        Long idMaintenance,
        @Schema(description = "mostrará el id del equipo que ingresó a mantenimiento", example = "1, 2, 3")
        Long idEquipment,
        @Schema(description  ="id del usuario que registró el mantenimiento", example = "1, 2, 3")
        Long idUserRegister,
        @Schema(description = "fecha de inicio en el que se empezó el mantenimiento", example = "03-04-2026")
        LocalDate startDate,
         @Schema(description = "fecha de finalización del mantenimiento", example = "05-04-2026")
        LocalDate endDate,
        @Schema(description = "descripción del por qué entró a mantenimiento")
        String description,
        @Schema(description = "precio del mantenimiento")
        String priceMaintenance,
        @Schema(description = "estado en el que se encuentra el mantenimiento", example = "ACTIVE, IN PROGRESS, etc")
        MaintenanceStatus maintenanceStatus
    
) {} 
