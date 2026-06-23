
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Schema(description = "respuesta que se le da al usuario del sistema")
@Getter
@Setter
public class UserResponseDTO {
    
    @Schema(description = "se le mostrará al user un entero", example = "1")
    private Long userId;
    @Schema(description = "se mostrará como respuesta el nombre que tenga el use guardado en la DBr", example ="Juan")
    private String name;
    @Schema(description = "se mostrará como respuesta el apellido que tenga el user guardado en la DB", example= "Perez")
    private String lastName;
    @Schema(description = "Se mostrará si se encuentra activo o no el user", example = "true/false")
    private Boolean active;
    @Schema(description = "Se mostrará como respuesta el cargo que tenga el user", example = "Jefe, empleado, etc")
    private String cargo;

    public UserResponseDTO(Long userId, String name, String lastName, Boolean active, String cargo) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.cargo = cargo;
    }
    
    
}
