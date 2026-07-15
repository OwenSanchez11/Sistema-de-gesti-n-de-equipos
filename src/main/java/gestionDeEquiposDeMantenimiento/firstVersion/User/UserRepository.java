
package gestionDeEquiposDeMantenimiento.firstVersion.User;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>, JpaSpecificationExecutor<UserModel> {
    boolean existsByDocumento(String documento);
    boolean existsByEmail(String email);
    Optional<UserModel>findByEmail(String email);
}
