
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "LoanUpdateDTO", description = "formato que recibe para actualizar el prestamo")
public class LoanUpdateDTO {
    @NotBlank(message = "oberservations return cannot be empty")
    @Schema(description = "Observación de retorno del equipo durante el prestamo")
    private String observationsReturn;
}
