
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Formato que recibe la api para la creación de usuarios")
@Getter
@Setter
public class UserCreateDTO {
    
    @Schema(description = "nombre del usuario", example ="Juan Antonio")
    @NotBlank(message = "Name cannot be empty")
    private String name;
    
     @Schema(description = "apellidos completos del usuario", example ="Perez Pertuz")
    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;
    
     @Schema(description = "email del usuario", example = "juanperez@example.com")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;
    
    @Schema(description = "documento de identidad de la persona y/o usuario", example = "123456789")
    @NotBlank(message = "Document cannot be empty")
    private String documento;
    
    @Schema(description = "número de telefono del usuario", example = "123456")
    @NotBlank(message = "PhoneNumber cannot be empty")
    private String phoneNumber;
    
    @Schema(description = "cargo que tiene el usuario dentro de la empresa", example= "Jefe, empleado, etc")
    @NotBlank(message = "Cargo cannot be empty")
    private String cargo;
    
    
    @Schema(description = "id del rol del usuario en el sistema", example ="1, 2, 3")
    @NotNull(message = "Rol ID is required")
    private Long idRol;
}
