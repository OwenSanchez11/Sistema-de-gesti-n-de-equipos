
package gestionDeEquiposDeMantenimiento.firstVersion.Loan;

import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanUpdateDTO;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/loan")
public class LoanController {
    private final LoanService loanService;
    
    
    @GetMapping(path = "/all")
    public List<LoanResponseDTO> getAllLoan() {
        return  this.loanService.getAllLoan();
    }
    
    @GetMapping(path = "/{idLoan}")
    public LoanResponseDTO getLoanById(@PathVariable Long idLoan) {
        return this.loanService.getLoanById(idLoan);
    }
    
    @PostMapping(path = "/saveLoan")
    public LoanResponseDTO saveNewLoan(@Valid @RequestBody LoanCreateDTO request){
        return this.loanService.saveLoan(request);
    }
    
    @PutMapping(path = "/updateLoan/{idLoan}")
    public LoanResponseDTO updateLoan(@Valid @RequestBody LoanUpdateDTO request, @PathVariable Long idLoan) {
        return this.loanService.updateLoan(request, idLoan);
    }

    @DeleteMapping(path = "/delete/{idLoan}")
    public String deleteLoan(@PathVariable Long idLoan) {
        Boolean ok = loanService.deleteLoan(idLoan);
        if (ok) {
            return "se borró con éxito el loan con id: "+ idLoan;
        }
        else {
            return "no se encontró el loan con id: "+ idLoan;
        }
    }

    
}
