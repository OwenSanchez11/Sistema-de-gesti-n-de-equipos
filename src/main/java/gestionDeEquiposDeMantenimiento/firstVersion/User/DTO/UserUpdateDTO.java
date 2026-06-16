
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    @NotBlank(message = "email cannot be empty")
    @Email(message = "invalid email format")
    private String email;
    @NotBlank(message = "name cannot be empty")
    private String name;
    
    private Boolean active;
    @NotBlank(message = "PhoneNumber cannot be empty")
    private String phoneNumber;
    @NotBlank(message = "Cargo cannot be empty")
    private String cargo;
    @NotNull(message = "Rol ID is required")
    private Long idRol;
}
