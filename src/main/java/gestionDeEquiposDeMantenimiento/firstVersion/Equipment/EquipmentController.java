
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
    
    @GetMapping(path = "/{id}")
    public Optional<EquipmentModel> getEquipmentById(@PathVariable Long id) {
        return this.EquipmentService.getEquipmentById(id);
    }
    
    @PostMapping(path = "/saveEquipment")
     public EquipmentModel saveEquipment(@RequestBody EquipmentModel request) {
         return this.EquipmentService.saveEquipment(request);
     }
     
     @PutMapping(path = "/update/{id}")
     public EquipmentModel updateEquipmentById(@RequestBody EquipmentUpdateDTO request, @PathVariable Long id) {
         return this.EquipmentService.updateEquipment(request, id);
     }
     
     @DeleteMapping(path = "/delete/{id}")
     public String deleteEquipment(@PathVariable Long id) {
         Boolean ok = this.EquipmentService.deleteEquipment(id);
         if (ok) {
             return "El equipo con el id: "+ id + " ha sido borrado con éxito";
         }
         else {
             return "No se encontró el equipo con el id: "+ id;
         }
     }
     
     
}
