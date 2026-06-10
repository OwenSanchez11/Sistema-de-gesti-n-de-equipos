
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceStatus;
import java.time.LocalDate;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record MaintenanceResponseDTO(
        Long idMaintenance,
        Long idEquipment,
        Long idUserRegister,
        LocalDate startDate,
        LocalDate endDate,
        String description,
        String priceMaintenance,
        MaintenanceStatus maintenanceStatus
    
) {} 
