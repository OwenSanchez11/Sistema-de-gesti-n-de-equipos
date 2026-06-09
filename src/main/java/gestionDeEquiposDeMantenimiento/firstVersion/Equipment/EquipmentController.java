
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentUpdateDTO;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/equipments")
public class EquipmentController {
    
    private final EquipmentService EquipmentService;
    
    
    @GetMapping(path = "/all")
    public List<EquipmentModel> getAllEquipments() {
        return this.EquipmentService.getAllEquipment();
} 
    
    @GetMapping(path = "/{idEquipment}")
    public Optional<EquipmentModel> getEquipmentById(@PathVariable Long idEquipment) {
        return this.EquipmentService.getEquipmentById(idEquipment);
    }
    
    @PostMapping(path = "/saveEquipment")
     public EquipmentModel saveEquipment(@RequestBody EquipmentModel request) {
         return this.EquipmentService.saveEquipment(request);
     }
     
     @PutMapping(path = "/update/{idEquipment}")
     public EquipmentModel updateEquipmentById(@RequestBody EquipmentUpdateDTO request, @PathVariable Long idEquipment) {
         return this.EquipmentService.updateEquipment(request, idEquipment);
     }
     
     @DeleteMapping(path = "/delete/{idEquipment}")
     public String deleteEquipment(@PathVariable Long idEquipment) {
         Boolean ok = this.EquipmentService.deleteEquipment(idEquipment);
         if (ok) {
             return "El equipo con el id: "+ idEquipment + " ha sido borrado con éxito";
         }
         else {
             return "No se encontró el equipo con el id: "+ idEquipment;
         }
     }
     
     
}
