
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentModel, Long>, JpaSpecificationExecutor<EquipmentModel> {
    boolean existsBycodeInventory(int codeInventory);
}
