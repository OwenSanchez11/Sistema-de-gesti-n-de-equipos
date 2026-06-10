
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanStatus;
import java.time.LocalDateTime;


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