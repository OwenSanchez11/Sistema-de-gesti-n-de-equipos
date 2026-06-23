
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Schema(description = "Formato en que se deben ingresar los datos para el update del usuario que se requiera")
@Getter
@Setter
public class UserUpdateDTO {
    @Schema(description = "email a ingresar si se desea actualizar el correo del user", example = "juanperez02@example.com")
    @NotBlank(message = "email cannot be empty")
    @Email(message = "invalid email format")
    private String email;
    
    @Schema(description = "nombre con el cual se quiere actualizar el username del usuario", example = "juan rodolfo")
    @NotBlank(message = "name cannot be empty")
    private String name;
    
    @Schema(description  ="campo si se desea cambiar el estado del usuario de activo/Desactivo", example = "true/false")
    private Boolean active;
    @Schema(description = "número de telefono con el que se quiere actualizar el campo de telefono del user", example ="12345612")
    @NotBlank(message = "PhoneNumber cannot be empty")
    private String phoneNumber;
    
    @Schema(description = "cargo con el que se desea actualizar este campo en la DB", example = "empleado, jefe")
    @NotBlank(message = "Cargo cannot be empty")
    private String cargo;
    @NotNull(message = "Rol ID is required")
    private Long idRol;
}
