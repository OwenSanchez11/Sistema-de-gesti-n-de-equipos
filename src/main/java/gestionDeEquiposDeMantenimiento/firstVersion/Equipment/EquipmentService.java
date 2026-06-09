
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentUpdateDTO;
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
   
    
    public EquipmentModel saveEquipment(EquipmentModel equip) {
        return EquipmentRepository.save(equip);
    }
    
    
    public EquipmentModel updateEquipment(EquipmentUpdateDTO request, Long idEquipment) {
        EquipmentModel equipment = EquipmentRepository.findById(idEquipment).get();
        equipment.setActive(request.getActive());
        equipment.setStatus(request.getStatus());
        
        return EquipmentRepository.save(equipment);
        
    }
    
 
    public Boolean deleteEquipment(Long idEquipment) {
        try {
            EquipmentRepository.deleteById(idEquipment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
    
}
