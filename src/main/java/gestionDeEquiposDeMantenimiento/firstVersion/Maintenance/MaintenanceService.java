
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.DTO.MaintenanceResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    
    
    public List<MaintenanceResponseDTO> getAllMaintenance() {
        List<MaintenanceModel> maintenance = maintenanceRepository.findAll();
        return maintenance.stream().map(m -> new MaintenanceResponseDTO(
                m.getIdMaintenance(), 
                m.getEquipment().getIdEquipment(), 
                m.getUserRegister().getIdUsuario(), 
                m.getStartDate(), 
                m.getEndDate(), 
                m.getDescription(), 
                m.getPriceMaintenance(), 
                m.getMaintenanceStatus())).toList();
    }
    
    
    public MaintenanceResponseDTO getMaintenanceById(Long idMaintenance) {
        MaintenanceModel maintenance = maintenanceRepository.findById(idMaintenance).orElseThrow(() -> new RuntimeException("Maintennace not found"));
        
        return new MaintenanceResponseDTO(maintenance.getIdMaintenance(), 
                maintenance.getEquipment().getIdEquipment(), 
                maintenance.getUserRegister().getIdUsuario(), LocalDate.now(), 
                maintenance.getEndDate(),
                maintenance.getDescription(),
                maintenance.getPriceMaintenance(), maintenance.getMaintenanceStatus());
        
    }
    
    
    
    public MaintenanceResponseDTO saveMaintenance(MaintenanceCreateDTO request) {
        MaintenanceModel maintenance = new MaintenanceModel();
        EquipmentModel idEquipment = equipmentRepository.findById(request.getIdEquipment()).orElseThrow(() -> new RuntimeException("Equipment not found"));
        UserModel idUser = userRepository.findById(request.getIdUser()).orElseThrow(() -> new RuntimeException("User not found"));
        
        maintenance.setEquipment(idEquipment);
        maintenance.setUserRegister(idUser);
        maintenance.setDescription(request.getDescription());
        maintenance.setStartDate(LocalDate.now());
        maintenance.setMaintenanceStatus(MaintenanceStatus.IN_PROGRESS);
        
        MaintenanceModel maintenanceSaved = maintenanceRepository.save(maintenance);
        
        return mapToResponse(maintenanceSaved);
        
        
    }
    
    private MaintenanceResponseDTO mapToResponse(MaintenanceModel maintenance) {
        return new MaintenanceResponseDTO(
                maintenance.getIdMaintenance(),
                maintenance.getEquipment().getIdEquipment(),
                maintenance.getUserRegister().getIdUsuario(),
                maintenance.getStartDate(),
                maintenance.getEndDate(),
                maintenance.getDescription(),
                maintenance.getPriceMaintenance(),
                maintenance.getMaintenanceStatus()
        );
}
    
    public MaintenanceResponseDTO updateMaintenance(Long idMaintenance) {
        MaintenanceModel maintenance = maintenanceRepository.findById(idMaintenance).orElseThrow(() -> new RuntimeException("Maintenance Not Found"));
        maintenance.setMaintenanceStatus(MaintenanceStatus.COMPLETED);
        maintenance.setEndDate(LocalDate.now());
        MaintenanceModel maintenanceSaved = maintenanceRepository.save(maintenance);
        
        return mapToResponse(maintenanceSaved);
    }
    
    
    
    
    
    
    
    
    
    
    
}
