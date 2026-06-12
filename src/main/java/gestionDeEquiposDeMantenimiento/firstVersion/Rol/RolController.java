

package gestionDeEquiposDeMantenimiento.firstVersion.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolService;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolUpdateDTO;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(path  ="/roles")
public class RolController {
    
            
    private final RolService rolService;
    
    @GetMapping(path = "/all")
    public List<RolModel> getAllRoles() {
        return this.rolService.getAllRoles();
    }
    
    @GetMapping(path = "/{idRol}")
    public Optional<RolModel> getRolById(@PathVariable Long idRol) {
        return this.rolService.getRolById(idRol);
    }
    
    
    @PostMapping(path = "/save")
    public RolModel saveRol(@RequestBody RolCreateDTO request) {
        return this.rolService.saveRol(request);
    }
    
    
    @PutMapping(path = "/updateRol/{idRol}")
    public RolModel updateRol(@RequestBody RolUpdateDTO request, @PathVariable Long idRol) {
        return this.rolService.updateRol(request, idRol);
    }
    
    @DeleteMapping(path = "/deleteRol/{idRol}")
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
