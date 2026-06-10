
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanStatus;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanCreateDTO {
    private Long idEquipment;
    private Long idUserReceiver;
    private Long idUserDeliverer;
    private String observationsOut; 
}
