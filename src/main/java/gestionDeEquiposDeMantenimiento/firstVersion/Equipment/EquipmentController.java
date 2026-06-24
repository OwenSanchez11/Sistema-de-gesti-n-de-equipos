
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO.EquipmentUpdateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

@Tag(name = "Equipments", description = "todas las operaciones relacionadas con los equipos del sistema")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/equipments")
public class EquipmentController {
    
    private final EquipmentService EquipmentService;
    
    @Operation(summary = "método para obtener todos los los equipos guardados en la DB")
    @GetMapping
    public List<EquipmentModel> getAllEquipments() {
        return this.EquipmentService.getAllEquipment();
} 
    
    @Operation(summary = "método para obtener los equipos con su id guardad en la DB")
    @GetMapping(path = "/{idEquipment}")
    public Optional<EquipmentModel> getEquipmentById(@PathVariable Long idEquipment) {
        return this.EquipmentService.getEquipmentById(idEquipment);
    }
    
    
    @Operation(summary = "método para guardar un equipo en la DB ")
    @PostMapping
     public EquipmentModel saveEquipment(@Valid @RequestBody EquipmentCreateDTO request) {
         return this.EquipmentService.saveEquipment(request);
     }
     
     @Operation(summary = "Método para actulizar un equipo")
     @PutMapping(path = "/{idEquipment}")
     public EquipmentModel updateEquipmentById(@Valid @RequestBody EquipmentUpdateDTO request, @PathVariable Long idEquipment) {
         return this.EquipmentService.updateEquipment(request, idEquipment);

     }
     
     @Operation(summary = "método para eliminar un equipo de la DB")
         @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description ="Equipo borrado con éxito"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Equipo no encontrado con el id ingresado",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "409",
                description = "CONFLICT - Database integrity violation",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
     @DeleteMapping(path = "/{idEquipment}")
     public String deleteEquipment(@PathVariable Long idEquipment) {
        EquipmentService.deleteEquipment(idEquipment);
        return "El equipo con el id: "+ idEquipment + " ha sido borrado con éxito";
     }
     
     
}
