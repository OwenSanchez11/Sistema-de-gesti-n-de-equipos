
package gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol.RolRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RolService {
    
    private final RolRepository rolRepository;
    
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
    
    public List<RolModel> getAllRoles() {
        return rolRepository.findAll();
    }
    
    public RolModel saveRol(RolModel rol) {
        return rolRepository.save(rol);
    }
    
    
}
