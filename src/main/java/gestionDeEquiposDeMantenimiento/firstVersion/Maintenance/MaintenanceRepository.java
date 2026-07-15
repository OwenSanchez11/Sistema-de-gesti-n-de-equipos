
package gestionDeEquiposDeMantenimiento.firstVersion.Maintenance;


import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<MaintenanceModel, Long>, JpaSpecificationExecutor<MaintenanceModel> {


}