
package gestionDeEquiposDeMantenimiento.firstVersion.Loan;

import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ErrorResponse;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Loan", description="Todas las operaciones relacionadas con los prestamos de equipos del sistema") 
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/loan")
public class LoanController {
    private final LoanService loanService;
    
    @Operation(summary = "método para obtener todos los prestamos guardados en la DB")
    @GetMapping
    public List<LoanResponseDTO> getAllLoan() {
        return  this.loanService.getAllLoan();
    }
    
    @Operation(summary = "método para obtener prestamos con su respectiva id con la que se guardó en la DB")
    @GetMapping(path = "/{idLoan}")
    public LoanResponseDTO getLoanById(@PathVariable Long idLoan) {
        return this.loanService.getLoanById(idLoan);
    }
    
    @Operation(summary = "método para crear y guardar un nuevo prestamo en la DB")
    @PostMapping
    public LoanResponseDTO saveNewLoan(@Valid @RequestBody LoanCreateDTO request){
        return this.loanService.saveLoan(request);
    }
    
    @Operation(summary = "método para actualizar el estado del prestamo en la DB usando las id de los prestamos")
    @PutMapping(path = "/{idLoan}")
    public LoanResponseDTO updateLoan(@Valid @RequestBody LoanUpdateDTO request, @PathVariable Long idLoan) {
        return this.loanService.updateLoan(request, idLoan);
    }

    @Operation(summary = "método para borrar prestamos de la DB")
        @ApiResponses(value = {
        @ApiResponse(
                responseCode = "200",
                description ="Prestamo borrado con éxito"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Prestamo no encontrado con el id ingresado",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "409",
                description = "CONFLICT - Database integrity violation",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
    @DeleteMapping(path = "/{idLoan}")
    public String deleteLoan(@PathVariable Long idLoan) {
     loanService.deleteLoan(idLoan);
     return "Se borró con éxito el prestamo con el id: " + idLoan;
    }

    
}
