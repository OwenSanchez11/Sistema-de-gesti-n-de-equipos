
package gestionDeEquiposDeMantenimiento.firstVersion.Exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Respuesta de error de la API")
public class ErrorResponse {
    @Schema(example = "2026-06-23T10:30:22")
    private LocalDateTime timeStamp;
    @Schema(example = "404, 409, 500, etc")
    private int status;
    private String error;
    @Schema(example = "User not found, internal server error, etc")
    private String message;
}
