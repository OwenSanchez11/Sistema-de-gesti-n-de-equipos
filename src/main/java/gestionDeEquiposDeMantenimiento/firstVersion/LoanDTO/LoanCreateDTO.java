
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "LoanCreateDTO",
    description = "Modelo de datos requerido para la creación de un nuevo préstamo de equipo"
)
public class LoanCreateDTO {
    @Schema(description = "Identificador único del equipo que se va a prestar", example = "105")
    @NotNull(message = "id Equipment is required")
    private Long idEquipment;
    
    @Schema(description = "Identificador del usuario que recibe el equipo",  example = "23")
    @NotNull(message = "id user receiver is required")
    private Long idUserReceiver;
    
    @Schema(description = "Identificador del usuario (o administrador) que entrega el equipo", example = "2")
    @NotNull(message = "id user deliverer is required")
    private Long idUserDeliverer;
    
   @Schema(description = "Observaciones o comentarios sobre el estado del equipo al momento de la salida", example = "El equipo se entrega con estuche rígido y cargador original. Sin rayones visibles.")
    @NotBlank(message = "observations cannot be empty")
    private String observationsOut; 
}
