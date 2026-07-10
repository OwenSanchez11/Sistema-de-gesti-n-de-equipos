
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
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Equipments", description = "todas las operaciones relacionadas con los equipos del sistema")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/equipments")
public class EquipmentController {
    
    private final EquipmentService EquipmentService;
    
    @Operation(summary = "método para obtener todos los los equipos guardados en la DB")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public Page<EquipmentModel> obtenerEquipments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return EquipmentService.obtenerEquipmentPorPagina(page, size);

    }
    
    @Operation(summary = "método para obtener los equipos con su id guardad en la DB")
    @GetMapping(path = "/{idEquipment}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public Optional<EquipmentModel> getEquipmentById(@PathVariable Long idEquipment) {
        return EquipmentService.getEquipmentById(idEquipment);
    }
    
    
    @Operation(summary = "método para guardar un equipo en la DB ")
    @PostMapping
    @PreAuthorize("hasRol('ADMIN')")
     public EquipmentModel saveEquipment(@Valid @RequestBody EquipmentCreateDTO request) {
         return EquipmentService.saveEquipment(request);
     }
     
     @Operation(summary = "Método para actulizar un equipo")
     @PutMapping(path = "/{idEquipment}")
     @PreAuthorize("hasRol('ADMIN')")
     public EquipmentModel updateEquipmentById(@Valid @RequestBody EquipmentUpdateDTO request, @PathVariable Long idEquipment) {
         return EquipmentService.updateEquipment(request, idEquipment);

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
     @PreAuthorize("hasRol('ADMIN')")
     public String deleteEquipment(@PathVariable Long idEquipment) {
        EquipmentService.deleteEquipment(idEquipment);
        return "El equipo con el id: "+ idEquipment + " ha sido borrado con éxito";
     }
     
     
}
