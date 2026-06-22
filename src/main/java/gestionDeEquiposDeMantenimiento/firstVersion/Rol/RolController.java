

package gestionDeEquiposDeMantenimiento.firstVersion.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolService;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rol", description = "todas las operaciones relacionadas con el manejo de los roles en la aplicación")
@RestController
@RequiredArgsConstructor
@RequestMapping(path  ="/roles")
public class RolController {
    
            
    private final RolService rolService;
    
    @Operation(summary = "método para obtener todos los roles existentes de la DB")
    @GetMapping
    public List<RolModel> getAllRoles() {
        return this.rolService.getAllRoles();
    }
    
    @Operation(summary = "método para obtener los roles con su respectiva id con la que se encuentra guardado en la DB")
    @GetMapping(path = "/{idRol}")
    public Optional<RolModel> getRolById(@PathVariable Long idRol) {
        return this.rolService.getRolById(idRol);
    }
    
    @Operation(summary = "método para crear y guardar roles dentro de la DB")
    @PostMapping
    public RolModel saveRol(@RequestBody RolCreateDTO request) {
        return this.rolService.saveRol(request);
    }
    
    @Operation(summary = "método para actualizar o editar los roles en la DB")
    @PutMapping(path = "/{idRol}")
    public RolModel updateRol(@RequestBody RolUpdateDTO request, @PathVariable Long idRol) {
        return this.rolService.updateRol(request, idRol);
    }
    
    @Operation(summary = "método para borrar roles dentro de la DB")
    @DeleteMapping(path = "/{idRol}")
    public String deleteRol(@PathVariable Long idRol) {
        Boolean ok = rolService.deleteRol(idRol);
        if (ok) {
            return "El rol con el id: " + idRol + " fue borrado con éxito";
        }
        else {
            return "algo falló en la operación o no se encontró el id: "+ idRol;
        }
    }
    
    
    
}
