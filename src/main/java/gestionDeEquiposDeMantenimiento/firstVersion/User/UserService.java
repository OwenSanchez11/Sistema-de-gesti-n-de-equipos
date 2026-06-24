
package gestionDeEquiposDeMantenimiento.firstVersion.User;

import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserUpdateDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    
    public List<UserResponseDTO> getAllUsers() {
        List<UserModel> users = userRepository.findAll();
        return users.stream().map(user -> new UserResponseDTO(user.getIdUsuario(), 
                user.getName(), user.getLastName(),user.getActive(), user.getCargo()
        ))
        .toList();
    }
    
    public UserResponseDTO getById(Long idUser) {
        UserModel user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        return new UserResponseDTO(user.getIdUsuario(), 
                user.getName(), 
                user.getLastName(), 
                user.getActive(), 
                user.getCargo());
    }
    
    
    public UserResponseDTO saveUser(UserCreateDTO request) {
        RolModel rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        UserModel user = new UserModel();
        user.setActive(Boolean.TRUE);
        user.setCargo(request.getCargo());
        
        boolean userExist =  userRepository.existsByDocumento(request.getDocumento());
        boolean emailExist = userRepository.existsByEmail(request.getEmail());
        
        
        if (userExist) {
            throw new BusinessRuleException("A user with this document already exists");
        }
        
        if (emailExist) {
            throw new BusinessRuleException("A user with this email already exists");
        }
     
        
        user.setDocumento(request.getDocumento());
        user.setEmail(request.getEmail());
        user.setLastName(request.getLastName());
        user.setName(request.getName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRol(rol);
        
        UserModel savedUser = userRepository.save(user);
        
        return mapToResponse(savedUser);
    }
    
    private UserResponseDTO mapToResponse(UserModel user) {
        return new UserResponseDTO(
                user.getIdUsuario(),
                user.getName(),
                user.getEmail(),
                user.getActive(),
                user.getCargo()
        );
    }
    
    
    public UserModel editarUser(UserUpdateDTO request, Long idUser) {
        UserModel user = userRepository.findById(idUser).get();
        RolModel rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        
        boolean emailExist = userRepository.existsByEmail(request.getEmail());
        
        if (emailExist) {
            throw new BusinessRuleException("A user with this email already exist");
        }
        
        user.setActive(request.getActive());
        user.setCargo(request.getCargo());
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRol(rol);

        return userRepository.save(user);
    }
    
    public void deleteUser(Long idUser) {
        UserModel user = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundException("No se encontró el user con el id: "+ idUser));
        userRepository.delete(user);
    }
    
    
}
