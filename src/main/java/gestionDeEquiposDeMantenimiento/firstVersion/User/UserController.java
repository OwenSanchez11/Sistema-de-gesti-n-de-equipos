
package gestionDeEquiposDeMantenimiento.firstVersion.User;

import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserResponseDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.User.DTO.UserUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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


@Tag(name = "Users", description = "todas las operaciones relacionada con el manejo de usuarios del sistema")
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {
    
    private final UserService userService;
    
    
    @Operation(summary = "método para obtener todos los usuarios de DB")
    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return this.userService.getAllUsers();
    }
    
    @Operation(summary = "método utilizado para obtener usuarios por su id con respecto a la DB")
    @GetMapping(path = "/{idUser}")
    public UserResponseDTO getUserById(@PathVariable Long idUser) {
        return this.userService.getById(idUser);
    }
    
    
    @Operation(summary = "método para crear y guardar usuarios en la DB")
    @PostMapping
    public UserResponseDTO createUser(@Valid @RequestBody UserCreateDTO request) {
        return this.userService.saveUser(request);
    }
    
    @Operation(summary = "método para editar parametros de los usuarios")
    @PutMapping(path = "/{idUser}")
    public UserModel editarUser(@Valid @RequestBody UserUpdateDTO request, @PathVariable Long id) {
        return this.userService.editarUser(request, id);
    }
    
    @Operation(summary = "método para eliminar usuarios de la DB")
    @DeleteMapping(path = "/{idUser}")
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
