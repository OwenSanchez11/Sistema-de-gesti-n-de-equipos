

package gestionDeEquiposDeMantenimiento.firstVersion.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ErrorResponse;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolService;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rol", description = "todas las operaciones relacionadas con el manejo de los roles en la aplicación")
@RestController
@RequiredArgsConstructor
@RequestMapping(path  ="/roles")
public class RolController {
    
            
    private final RolService rolService;
    
    @Operation(summary = "método para obtener todos los roles existentes de la DB")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "roles encontrados"
        ),
        @ApiResponse(
                responseCode = "204",
                description = "No content"
        )
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<RolModel> getAllRoles() {
        return rolService.getAllRoles();
    }
    
    @Operation(summary = "método para obtener los roles con su respectiva id con la que se encuentra guardado en la DB")
    @ApiResponses(value = {
        @ApiResponse( 
            responseCode = "200",
            description = "Rol encontrado"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "no se encontró en la DB un rol con esa ID",
            content = @Content(
                schema = @Schema(implementation = ErrorResponse.class)
        )),
        @ApiResponse
    })
    @GetMapping(path = "/{idRol}")
    @PreAuthorize("hasRole('ADMIN')")
    public RolModel getRolById(@PathVariable Long idRol) {
        return rolService.getRolById(idRol);
    }
    
    @Operation(summary = "método para crear y guardar roles dentro de la DB")
    @ApiResponses(value = {
        @ApiResponse( 
            responseCode = "200",
            description = "Rol creado exitosamente"
        ),
        @ApiResponse( 
            responseCode = "409",
            description = "Ya existe el rol con ese nombre"
        ),
    
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                schema = @Schema(implementation = ErrorResponse.class)
        )),
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public RolModel saveRol(@RequestBody RolCreateDTO request) {
        return rolService.saveRol(request);
    }
    
    @Operation(summary = "método para actualizar o editar los roles en la DB")
    @ApiResponses(value = {
        @ApiResponse( 
            responseCode = "200",
            description = "Rol creado exitosamente"
        ),
        @ApiResponse( 
            responseCode = "409",
            description = "Ya existe el rol con ese nombre"
        ),
    
        @ApiResponse(
            responseCode = "500",
            description = "Internal Server Error",
            content = @Content(
                schema = @Schema(implementation = ErrorResponse.class)
        )),
    })
    @PutMapping(path = "/{idRol}")
    @PreAuthorize("hasRole('ADMIN')")
    public RolModel updateRol(@RequestBody RolUpdateDTO request, @PathVariable Long idRol) {
        return rolService.updateRol(request, idRol);
    }
    
    @Operation(summary = "método para borrar roles dentro de la DB")
        @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description ="Rol borrado con éxito"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Rol no encontrado con el id ingresado",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
                
        ),
        @ApiResponse(
                responseCode = "409",
                description = "CONFLICT - Database integrity violation",
                content = @Content(
                    schema = @Schema(implementation = ErrorResponse.class)
        )),
    })
    @DeleteMapping(path = "/{idRol}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteRol(@PathVariable Long idRol) {
        rolService.deleteRol(idRol);
        return "rol con el id " + idRol + "ha sido borrado con éxito";
    }
}
