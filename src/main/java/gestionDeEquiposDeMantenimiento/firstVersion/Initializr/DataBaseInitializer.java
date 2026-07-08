package gestionDeEquiposDeMantenimiento.firstVersion.Initializr;

import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataBaseInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void initializeRole() {

        if (!rolRepository.existsByName("ADMIN")) {
            RolModel rol = new RolModel();
            rol.setName("ADMIN");

            rolRepository.save(rol);

        }
    }

    private void initializeAdminUser() {

        RolModel adminRole = rolRepository.findByName("ADMIN")
                .orElseThrow(() -> new IllegalStateException("El rol ADMIN no existe."));

        if (!userRepository.existsByEmail("admin@admin.com")) {

            UserModel user = new UserModel();
            user.setName("ADMIN");
            user.setLastName("ADMIN");
            user.setEmail("admin@admin.com");
            user.setPassword(passwordEncoder.encode("123456"));
            user.setActive(true);
            user.setDocumento("!11111");
            user.setCargo("admin");
            user.setPhoneNumber("11111");
            user.setRol(adminRole);

            userRepository.save(user);
        }

    }

    @Override
    public void run(String... args) throws Exception {
        initializeRole();
        initializeAdminUser();

    }

}
