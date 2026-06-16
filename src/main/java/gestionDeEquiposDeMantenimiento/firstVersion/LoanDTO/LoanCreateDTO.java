
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanCreateDTO {
    @NotNull(message = "id Equipment is required")
    private Long idEquipment;
    @NotNull(message = "id user receiver is required")
    private Long idUserReceiver;
    @NotNull(message = "id user deliverer is required")
    private Long idUserDeliverer;
    @NotBlank(message = "observations cannot be empty")
    private String observationsOut; 
}
