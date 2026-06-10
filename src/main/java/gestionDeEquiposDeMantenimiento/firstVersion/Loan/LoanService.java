
package gestionDeEquiposDeMantenimiento.firstVersion.Loan;



import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanUpdateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanService {
    
    private final LoanRepository loanRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;
    
    
    public List<LoanResponseDTO> getAllLoan() {
         List<LoanModel> loans = loanRepository.findAll();
         return loans.stream().map(loan -> new LoanResponseDTO(loan.getIdLoan(), 
                 loan.getEquipment().getIdEquipment(), 
                 loan.getEquipment().getName(), 
                 loan.getUserReceiver().getName(), loan.getUserDeliverer().getName(), loan.getObservationsOut(),loan.getObservationsReturn(),
                 LocalDateTime.now(), LoanStatus.ACTIVE)).toList();
    }
    
    public LoanResponseDTO getLoanById(Long idLoan) {
        LoanModel loan = loanRepository.findById(idLoan).orElseThrow(() -> new RuntimeException("Loan not found"));
        
        return new LoanResponseDTO(loan.getIdLoan(), 
                 loan.getEquipment().getIdEquipment(), 
                 loan.getEquipment().getName(), 
                 loan.getUserReceiver().getName(), loan.getUserDeliverer().getName(),loan.getObservationsOut(),
                loan.getObservationsReturn(), 
                 LocalDateTime.MIN, LoanStatus.ACTIVE);
    }
    
    
    public LoanResponseDTO saveLoan(LoanCreateDTO request) {
        LoanModel loan = new LoanModel();
        EquipmentModel equipment = equipmentRepository.findById(request.getIdEquipment()).get();
        UserModel userReceiver = userRepository.findById(request.getIdUserReceiver()).orElseThrow(() -> new RuntimeException("User not found"));
        UserModel userDeliverer = userRepository.findById(request.getIdUserDeliverer()).orElseThrow(() -> new RuntimeException("User not found"));
                
        loan.setEquipment(equipment);
        loan.setUserReceiver(userReceiver);
        loan.setUserDeliverer(userDeliverer);
        loan.setLoanDate(LocalDateTime.now());
        loan.setObservationsOut(request.getObservationsOut());
        loan.setLoanStatus(LoanStatus.ACTIVE);
        
        LoanModel loanSaved = loanRepository.save(loan);
        return mapToResponse(loanSaved);
       
        
    }

    private LoanResponseDTO mapToResponse(LoanModel loan) {
        return new LoanResponseDTO(loan.getIdLoan(), loan.getEquipment().getIdEquipment(),
                loan.getEquipment().getName(), loan.getUserReceiver().getName(), loan.getUserDeliverer().getName(), 
                loan.getObservationsOut(), loan.getObservationsReturn() ,
                LocalDateTime.now(), LoanStatus.ACTIVE);
    }
        
   
    public LoanResponseDTO updateLoan(LoanUpdateDTO request, Long idLoan) {
        LoanModel loan = loanRepository.findById(idLoan).orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setLoanStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());
        loan.setObservationsReturn(request.getObservationsReturn());
        LoanModel loanSaved = loanRepository.save(loan);
        return mapToResponse(loanSaved);
    }
    
    
    
 public Boolean deleteLoan(Long idLoan) {
        try {
            loanRepository.deleteById(idLoan);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    
}
