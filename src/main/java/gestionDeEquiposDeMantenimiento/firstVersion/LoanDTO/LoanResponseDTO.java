
package gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import gestionDeEquiposDeMantenimiento.firstVersion.Loan.LoanStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(
    name = "LoanResponseDTO",
    description = "Modelo de respuesta que representa la información detallada de un préstamo"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record LoanResponseDTO(
    
   @Schema(description = "Identificador único del préstamo", example = "45")    
    Long idLoan,
    
    @Schema(description = "Identificador único del equipo prestado", example = "105")
    Long idEquipment,
    
    @Schema(description = "Nombre o modelo del equipo", example = "Laptop Dell Latitude 5420")
    String equipmentName,
    
    @Schema(description = "Nombre completo del usuario que recibió el equipo", example = "John Doe")
    String receiverName,
    
    @Schema(description = "Nombre completo del usuario/admin que entregó el equipo", example = "Jane Smith")
    String delivererName,
    
    @Schema(description = "Observaciones registradas al momento de la salida del equipo", example = "Se entrega con cargador.")
    String observationsOut,
    
    @Schema(
        description = "Observaciones registradas al momento de la devolución (puede ser nulo si está activo)", 
        example = "Devuelto a tiempo y en óptimas condiciones."
    )
    String observationsReturn,
    
    @Schema(
        description = "Fecha y hora exacta en la que se realizó el préstamo", 
        example = "2026-06-22T15:30:00",
        type = "string",
        format = "date-time"
    )
    LocalDateTime loanDate,
    
    @Schema(
        description = "Estado actual del préstamo", 
        example = "ACTIVE",
        allowableValues = {"ACTIVE", "RETURNED, etc"} 
    )
    LoanStatus loanStatus
) {}