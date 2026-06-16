
package gestionDeEquiposDeMantenimiento.firstVersion.Loan;



import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.LoanDTO.LoanUpdateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Maintenance.MaintenanceRepository;
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
                 loan.getUserReceiver().getName(), loan.getUserDeliverer().getName(),
                 loan.getObservationsOut(),loan.getObservationsReturn(),
                 loan.getLoanDate(), loan.getLoanStatus())).toList();
    }
    
    public LoanResponseDTO getLoanById(Long idLoan) {
        LoanModel loan = loanRepository.findById(idLoan).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        
        return new LoanResponseDTO(loan.getIdLoan(), 
                 loan.getEquipment().getIdEquipment(), 
                 loan.getEquipment().getName(), 
                 loan.getUserReceiver().getName(), loan.getUserDeliverer().getName(),loan.getObservationsOut(),
                loan.getObservationsReturn(), 
                 loan.getLoanDate(), loan.getLoanStatus());
    }
    
    
    public LoanResponseDTO saveLoan(LoanCreateDTO request) {
        LoanModel loan = new LoanModel();
        
        EquipmentModel equipment = equipmentRepository.findById(request.getIdEquipment()).orElseThrow(() -> new ResourceNotFoundException("Equipment Not Found"));
        UserModel userReceiver = userRepository.findById(request.getIdUserReceiver()).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        UserModel userDeliverer = userRepository.findById(request.getIdUserDeliverer()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
  
        
        Optional<LoanModel> activeLoan = loanRepository.findByEquipmentAndLoanStatus(equipment, LoanStatus.ACTIVE);
        
        if (activeLoan.isPresent()) {
            throw  new BusinessRuleException("Equipment already has an active loan");
        }
        
        if (!equipment.getActive()) {
            throw new BusinessRuleException("Equipment inactive");
        }
        
        if (!userReceiver.getActive()) {
            throw new BusinessRuleException("User is inactive");
        }
        
        if (!userDeliverer.getActive()) {
            throw new BusinessRuleException("User is inactive");
        }
        
        loan.setEquipment(equipment);
        loan.setUserReceiver(userReceiver);
        loan.setUserDeliverer(userDeliverer);
        loan.setLoanDate(LocalDateTime.now());
        loan.setObservationsOut(request.getObservationsOut());
        loan.setLoanStatus(LoanStatus.ACTIVE);
        
        equipment.setActive(Boolean.FALSE);
        equipmentRepository.save(equipment);
            
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
        LoanModel loan = loanRepository.findById(idLoan).orElseThrow(() -> new ResourceNotFoundException("Loan not found"));
        EquipmentModel equipment = loan.getEquipment();
        
        if (loan.getLoanStatus() == LoanStatus.RETURNED) {
            throw new BusinessRuleException("Loan already returned");
        }
        
        loan.setLoanStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());
        loan.setObservationsReturn(request.getObservationsReturn());
        
        equipment.setActive(Boolean.TRUE);
        equipmentRepository.save(equipment);
        
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
