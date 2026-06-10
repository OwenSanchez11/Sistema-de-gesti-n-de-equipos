
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance;

import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {
    private final MaintenanceService maintenanceService;
    
    @GetMapping(path = "/all")
    public List<MaintenanceResponseDTO> getAll() {
        return this.maintenanceService.getAllMaintenance();
    }
    
    @GetMapping(path = "/getById/{idMaintenance}")
    public MaintenanceResponseDTO getMaintenanceById(@PathVariable Long idMaintenance) {
        return this.maintenanceService.getMaintenanceById(idMaintenance);
    }
    
    @PostMapping(path = "/saveMaintenance")
    public MaintenanceResponseDTO saveMaintenance(@RequestBody MaintenanceCreateDTO request) {
        return this.maintenanceService.saveMaintenance(request);
    }
    
    
    @PutMapping(path = "/updateMaintenance/{idMaintenance}")
    public MaintenanceResponseDTO updateMaintenance(@PathVariable Long idMaintenance) {
        return this.maintenanceService.updateMaintenance(idMaintenance);
    }
    
    
    
}
