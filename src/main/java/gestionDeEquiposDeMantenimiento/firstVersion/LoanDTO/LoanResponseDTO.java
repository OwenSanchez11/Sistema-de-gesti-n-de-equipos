
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanStatus;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoanResponseDTO(
    Long idLoan,
    Long idEquipment,
    String equipmentName,
    String receiverName,
    String delivererName,
    String observationsOut,
    String observationsReturn,
    LocalDateTime loanDate,
    LoanStatus loanStatus
) {}