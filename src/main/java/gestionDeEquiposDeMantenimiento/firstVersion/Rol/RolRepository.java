
package gestionDeEquiposDeMantenimiento.firstVersion.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Long> {
    
}
