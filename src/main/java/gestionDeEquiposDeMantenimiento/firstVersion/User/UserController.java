
package gestionDeEquiposDeMantenimiento.firstVersion.User;

import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserUpdateDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    
    private final UserService userService;
    
    
    @GetMapping(path = "/all")
    public List<UserResponseDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }
    
    @GetMapping(path = "/getUser/{idUser}")
    public UserResponseDTO getUserById(@PathVariable Long idUser) {
        return this.userService.getById(idUser);
    }
    
    @PostMapping(path = "/createUser")
    public UserResponseDTO createUser(@RequestBody UserCreateDTO request) {
        return this.userService.saveUser(request);
    }
    
    @PutMapping(path = "/editarUser/{id}")
    public UserModel editarUser(@RequestBody UserUpdateDTO request, @PathVariable Long id) {
        return this.userService.editarUser(request, id);
    }
    
    @DeleteMapping(path = "/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        Boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return "el usuario con id: "+ id + "ha sido borrado con éxito";
        }
        else {
            return "No se encontró el usuario con el id: " + id;
        }
    }
    
    
    
}
