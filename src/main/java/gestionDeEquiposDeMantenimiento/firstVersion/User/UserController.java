
package gestionDeEquiposDeMantenimiento.firstVersion.User;

import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ErrorResponse;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
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


@Tag(name = "Users", description = "todas las operaciones relacionada con el manejo de usuarios del sistema")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    
    private final UserService userService;

    @Operation(summary = "Obtener todos usuarios")
    @ApiResponses(value = {

        @ApiResponse(
            responseCode = "200",
            description = "Usuario encontrado"
        ),

        @ApiResponse(
            responseCode = "204",
            description = "No content",
            content = @Content(
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public List<UserResponseDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }
    
    @Operation(summary = "método utilizado para obtener usuarios por su id con respecto a la DB")
    @ApiResponses(value = {
        @ApiResponse( 
            responseCode = "200",
            description = "Usuario encontrado"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "no se encontró en la DB un usuario con esa ID",
            content = @Content(
                schema = @Schema(implementation = ErrorResponse.class)
        ))
    })
    @GetMapping(path = "/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO getUserById(@PathVariable Long idUser) {
        return this.userService.getById(idUser);
    }
    
    
    @Operation(summary = "método para crear y guardar usuarios en la DB")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Usuario creado"
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Ya existe un usuario con esas credenciales",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        
        @ApiResponse(
                responseCode = "500",
                description = "Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateDTO request) {
        return this.userService.saveUser(request);
    }
    
    @Operation(summary = "método para editar parametros de los usuarios")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description = "Usuario modificado correctamente"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "409",
                description = "ya existe un usuario con esas credenciales",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
    @PutMapping(path = "/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserModel editarUser(@Valid @RequestBody UserUpdateDTO request, @PathVariable Long idUser) {
        return this.userService.editarUser(request, idUser);
    }
    
    @Operation(summary = "método para eliminar usuarios de la DB")
    @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description ="Usuario borrado con éxito"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "usuario no encontrado con el id ingresado",
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
    @DeleteMapping(path = "/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
    }
    
    
    
}
