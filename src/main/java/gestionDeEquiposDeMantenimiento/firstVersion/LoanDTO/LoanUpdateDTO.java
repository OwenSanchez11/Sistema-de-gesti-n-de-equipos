
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanUpdateDTO {
    @NotBlank(message = "oberservations return cannot be empty")
    private String observationsReturn;
}
