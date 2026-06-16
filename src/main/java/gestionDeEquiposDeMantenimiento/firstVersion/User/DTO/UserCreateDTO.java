
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;


@Getter
@Setter
public class UserCreateDTO {
    
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Last Name cannot be empty")
    private String lastName;
    
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Document cannot be empty")
    private String documento;
    @NotBlank(message = "PhoneNumber cannot be empty")
    private String phoneNumber;
    @NotBlank(message = "Cargo cannot be empty")
    private String cargo;
    
    @NotNull(message = "Rol ID is required")
    private Long idRol;
}
