
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentUpdateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    
    
    private final EquipmentRepository EquipmentRepository;
    
    public List<EquipmentModel> getAllEquipment() {
        return EquipmentRepository.findAll();
    }
    
    public Optional<EquipmentModel> getEquipmentById(Long idEquipment) 
    {
        return EquipmentRepository.findById(idEquipment);
    }
   
    
    public EquipmentModel saveEquipment(EquipmentCreateDTO equip) {
        EquipmentModel equipment = new EquipmentModel();
        equipment.setName(equip.getName());
        equipment.setCodeInventory(equip.getCodeInventory());
        equipment.setDescription(equip.getDescription());
        equipment.setBrand(equip.getBrand());
        equipment.setModel(equip.getModel());
        equipment.setSeriesNum(equip.getSeriesNum());
        equipment.setStatus(equip.getStatus());
        equipment.setLocation(equip.getLocation());
        equipment.setActive(Boolean.TRUE);
        
        return EquipmentRepository.save(equipment);
    }
    
    
    public EquipmentModel updateEquipment(EquipmentUpdateDTO request, Long idEquipment) {
        EquipmentModel equipment = EquipmentRepository.findById(idEquipment).get();
        equipment.setActive(request.getActive());
        equipment.setStatus(request.getStatus());
        
        return EquipmentRepository.save(equipment);
        
    }
    
 
    public void deleteEquipment(Long idEquipment) {
        EquipmentModel equipment = EquipmentRepository.findById(idEquipment).orElseThrow(() -> new ResourceNotFoundException("No se encontró un equipo con el id: "+ idEquipment));
        EquipmentRepository.deleteById(idEquipment);
    }
    
    
    
    
}
