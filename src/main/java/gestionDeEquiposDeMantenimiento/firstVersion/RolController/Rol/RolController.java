

package gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol.RolService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @PostMapping(path = "/save")
    public RolModel saveRol(@RequestBody RolModel request) {
        return this.rolService.saveRol(request);
    }
    
    
    
    
}
