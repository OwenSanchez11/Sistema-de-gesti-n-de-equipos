
package gestionDeEquiposDeMantenimiento.firstVersion.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<RolModel, Long> {
    boolean existsByName(String name);
    Optional<RolModel> findByName(String name);

}
