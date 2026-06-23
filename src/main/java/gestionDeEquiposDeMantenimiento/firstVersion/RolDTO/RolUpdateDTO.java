
package gestionDeEquiposDeMantenimiento.firstVersion.RolDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "formato para actualizar el nombre de un rol en la DB")
public class RolUpdateDTO {
    @NotBlank
    @Schema(description = "nombre a actualizar el rol")
    private String name;
}
