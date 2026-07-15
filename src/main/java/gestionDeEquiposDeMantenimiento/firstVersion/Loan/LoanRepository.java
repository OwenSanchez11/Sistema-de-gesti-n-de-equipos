
package gestionDeEquiposDeMantenimiento.firstVersion.Loan;

import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanModel, Long>, JpaSpecificationExecutor<LoanModel> {

}
