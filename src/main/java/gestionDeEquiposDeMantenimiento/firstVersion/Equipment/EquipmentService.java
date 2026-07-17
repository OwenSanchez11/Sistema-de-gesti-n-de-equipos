
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentUpdateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EquipmentService {
    
    
    private final EquipmentRepository EquipmentRepository;
    
    public Page<EquipmentResponseDTO> obtenerEquipmentPorPagina(int numPagina, int tamañoPagina, String sortBy, Sort.Direction direction, String name, Integer seriesNum) {
        Pageable pageable = PageRequest.of(numPagina, tamañoPagina, Sort.by(direction, sortBy));
        Page<EquipmentModel> equipment;

        Specification<EquipmentModel> spec = Specification.unrestricted();
        spec = spec.and(EquipmentSpecification.hasEquipmentName(name));
        spec = spec.and(EquipmentSpecification.hasEquipmentSeriesNum(seriesNum));

        equipment = EquipmentRepository.findAll(spec, pageable);

        return equipment.map(this::mapToResponse);
    }
    
    public EquipmentResponseDTO getEquipmentById(Long idEquipment)  {

        EquipmentModel equipment = EquipmentRepository.findById(idEquipment).orElseThrow(() -> new ResourceNotFoundException("Equipment not found"));


        return new EquipmentResponseDTO(
                equipment.getIdEquipment(),
                equipment.getCodeInventory(),
                equipment.getName(),
                equipment.getDescription(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getSeriesNum(),
                equipment.getStatus(),
                equipment.getLocation(),
                equipment.getActive()
                );
    }
   
    
    public EquipmentResponseDTO saveEquipment(EquipmentCreateDTO equip) {

        EquipmentModel equipment = new EquipmentModel();
        boolean EquipmentExistsByCodeInventory = EquipmentRepository.existsBycodeInventory(equip.getCodeInventory());

        if (EquipmentExistsByCodeInventory) {
            throw new BusinessRuleException("A equipment with that code already Exists");
        }

        equipment.setName(equip.getName());
        equipment.setCodeInventory(equip.getCodeInventory());
        equipment.setDescription(equip.getDescription());
        equipment.setBrand(equip.getBrand());
        equipment.setModel(equip.getModel());
        equipment.setSeriesNum(equip.getSeriesNum());
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipment.setLocation(equip.getLocation());
        equipment.setActive(Boolean.TRUE);

        EquipmentModel newEquipment = EquipmentRepository.save(equipment);

        return mapToResponse(newEquipment);
    }
    
    
    public EquipmentModel updateEquipment(EquipmentUpdateDTO request, Long idEquipment) {
        EquipmentModel equipment = EquipmentRepository.findById(idEquipment).get();
        equipment.setActive(request.getActive());
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        
        return EquipmentRepository.save(equipment);
        
    }
    
 
    public void deleteEquipment(Long idEquipment) {
        EquipmentModel equipment = EquipmentRepository.findById(idEquipment).orElseThrow(() -> new ResourceNotFoundException("No se encontró un equipo con el id: "+ idEquipment));
        EquipmentRepository.deleteById(idEquipment);
    }
    
    

    private EquipmentResponseDTO mapToResponse(EquipmentModel equipment) {
        return new EquipmentResponseDTO(
                equipment.getIdEquipment(),
                equipment.getCodeInventory(),
                equipment.getName(),
                equipment.getDescription(),
                equipment.getBrand(),
                equipment.getModel(),
                equipment.getSeriesNum(),
                equipment.getStatus(),
                equipment.getLocation(),
                equipment.getActive()
        );
    }

    
}
