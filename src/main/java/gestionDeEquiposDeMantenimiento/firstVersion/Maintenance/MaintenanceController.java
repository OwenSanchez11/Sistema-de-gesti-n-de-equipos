
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance;

import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Maintenance", description= "Todas las operaciones relacionadas con el manejo de mantenimientos del sistema")
@RestController
@RequestMapping(path = "/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {
    private final MaintenanceService maintenanceService;
    
    @Operation(summary = "método para obtener todos los mantenimientos que se encuentran guardados en la DB")
    @GetMapping
    @PreAuthorize("hasAnyRol('ADMIN', 'TECNICO')")
    public List<MaintenanceResponseDTO> getAll() {
        return this.maintenanceService.getAllMaintenance();
    }
    
    @Operation(summary = " método para obtener un mantenimiento con la id con la que se guardó en la DB")
    @GetMapping(path = "/{idMaintenance}")
    @PreAuthorize("hasAnyRol('ADMIN', 'TECNICO')")
    public MaintenanceResponseDTO getMaintenanceById(@PathVariable Long idMaintenance) {
        return this.maintenanceService.getMaintenanceById(idMaintenance);
    }
    
    @Operation(summary = "método para crear y guardar un nuevo mantenimiento en la DB")
    @PostMapping
    @PreAuthorize("hasAnyRol('ADMIN', 'TECNICO')")
    public MaintenanceResponseDTO saveMaintenance(@Valid @RequestBody MaintenanceCreateDTO request) {
        return this.maintenanceService.saveMaintenance(request);
    }
    
    @Operation(summary = "método para actualizar el estado del mantenimiento en la DB")
    @PutMapping(path = "/{idMaintenance}")
    @PreAuthorize("hasAnyRol('ADMIN', 'TECNICO')")
    public MaintenanceResponseDTO updateMaintenance(@PathVariable Long idMaintenance) {
        return this.maintenanceService.updateMaintenance(idMaintenance);
    }
    
    
    
}
