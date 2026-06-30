
package gestionDeEquiposDeMantenimiento.firstVersion.auth.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Schema(description = "formato que debe recibir el login para loguearse")
@Setter
public class LoginRequestDTO {
    @Schema(description = "email del usuario", example ="JuanAntonio@example.com")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    
    @Schema(description = "Password del usuario", example ="Juan123456%%$")
    @NotBlank(message = "Password cannot be empty")
    private String password;
}
