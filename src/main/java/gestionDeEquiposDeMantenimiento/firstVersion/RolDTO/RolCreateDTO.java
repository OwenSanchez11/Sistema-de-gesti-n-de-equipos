
package gestionDeEquiposDeMantenimiento.firstVersion.RolDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description  ="formato que recibe la API para la creación de un nuevo rol en la DB")
public class RolCreateDTO {
    
    @Schema(description = "nombre del nuevo rol")
    @NotBlank
    private String name;
}
