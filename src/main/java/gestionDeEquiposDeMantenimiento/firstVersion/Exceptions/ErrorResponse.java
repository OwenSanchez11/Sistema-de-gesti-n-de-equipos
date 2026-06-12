
package gestionDeEquiposDeMantenimiento.firstVersion.Exceptions;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String message;
}
