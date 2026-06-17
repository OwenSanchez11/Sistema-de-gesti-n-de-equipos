
package gestionDeEquiposDeMantenimiento.firstVersion.Loan;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;

@Entity
@Table(name = "loan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoanModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLoan;
    
    @ManyToOne
    @JoinColumn(name = "id_equipment")
    private EquipmentModel equipment;
    
    @ManyToOne
    @JoinColumn(name = "id_user_reciever")
    private UserModel userReceiver;
    
    @ManyToOne
    @JoinColumn(name = "id_user_deliverer")
    private UserModel userDeliverer;
    
    @Column
    private LocalDateTime loanDate;
    @Column
    private LocalDateTime returnDate;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "loan_status")
    private LoanStatus loanStatus;
        
   @Column
    private String observationsOut;
   @Column
    private String observationsReturn;

    
            
    
    
}
