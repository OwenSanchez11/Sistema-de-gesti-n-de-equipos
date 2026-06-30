
package gestionDeEquiposDeMantenimiento.firstVersion.auth;

import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.InvalidCredentialsException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserModel;
import gestionDeEquiposDeMantenimiento.firstVersion.User.UserRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.auth.DTO.LoginRequestDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.auth.DTO.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public LoginResponseDTO authenticate(LoginRequestDTO request) {
        UserModel user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Unauthorized credentiales"));
        String hashedPassword = user.getPassword();
        
        
        if (passwordEncoder.matches(request.getPassword(), hashedPassword)) {
            
            return new LoginResponseDTO("User autenticado con éxito", Boolean.TRUE);
            
        }
       throw new InvalidCredentialsException("Unauthorized");
    
    }

}
