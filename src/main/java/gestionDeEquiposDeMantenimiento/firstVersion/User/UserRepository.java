
package gestionDeEquiposDeMantenimiento.firstVersion.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    boolean existsByDocumento(String documento);
    boolean existsByEmail(String email);
    Optional<UserModel>findByEmail(String email);
}
