
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance;

import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Maintenance", description= "Todas las operaciones relacionadas con el manejo de mantenimientos del sistema")
@RestController
@RequestMapping(path = "/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {
    private final MaintenanceService maintenanceService;
    
    @Operation(summary = "método para obtener todos los mantenimientos que se encuentran guardados en la DB")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public Page<MaintenanceResponseDTO> getMaintenance(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "idMaintenance") String sortyBy,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            @RequestParam(required = false) Long idEquipment

    ) {

        return maintenanceService.obtenerMantenimientosPorPagina(page, size, sortyBy, direction, idEquipment);
    }
    
    @Operation(summary = " método para obtener un mantenimiento con la id con la que se guardó en la DB")
    @GetMapping(path = "/{idMaintenance}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public MaintenanceResponseDTO getMaintenanceById(@PathVariable Long idMaintenance) {
        return maintenanceService.getMaintenanceById(idMaintenance);
    }
    
    @Operation(summary = "método para crear y guardar un nuevo mantenimiento en la DB")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public MaintenanceResponseDTO saveMaintenance(@Valid @RequestBody MaintenanceCreateDTO request) {
        return maintenanceService.saveMaintenance(request);
    }
    
    @Operation(summary = "método para actualizar el estado del mantenimiento en la DB")
    @PutMapping(path = "/{idMaintenance}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public MaintenanceResponseDTO updateMaintenance(@PathVariable Long idMaintenance) {
        return maintenanceService.updateMaintenance(idMaintenance);
    }
    
    
    
}
