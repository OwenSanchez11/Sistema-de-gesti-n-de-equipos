
package gestionDeEquiposDeMantenimiento.firstVersion.Loan;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long> {

    Optional<LoanModel> findByEquipmentAndLoanStatus(
            EquipmentModel equipment,
            LoanStatus loanStatus
    );
}
