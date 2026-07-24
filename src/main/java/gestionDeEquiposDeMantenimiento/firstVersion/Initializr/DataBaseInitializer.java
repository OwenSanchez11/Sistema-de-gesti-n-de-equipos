package gestionDeEquiposDeMantenimiento.firstVersion.Initializr;

import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolName;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataBaseInitializer implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.email}")
    private String adminEmail;

    @Value("${app.admin.password}")
    private String adminPassword;

    public DataBaseInitializer(RolRepository rolRepository,
                               UserRepository userRepository,
                               PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private void initializeRole() {

        createRoleIfNotExists(RolName.ADMIN);
        createRoleIfNotExists(RolName.TECNICO);

    }

    private void createRoleIfNotExists(RolName roleName) {

        if (!rolRepository.existsByName(roleName)) {
            RolModel role = new RolModel();
            role.setName(roleName);
            rolRepository.save(role);
        }

    }

    private void initializeAdminUser() {

        RolModel adminRole = rolRepository.findByName(RolName.ADMIN)
                .orElseThrow(() -> new IllegalStateException("El rol ADMIN no existe."));

        if (!userRepository.existsByEmail(adminEmail)) {

            UserModel user = new UserModel();
            user.setName("ADMIN");
            user.setLastName("ADMIN");
            user.setEmail(adminEmail);
            user.setPassword(passwordEncoder.encode(adminPassword));
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
