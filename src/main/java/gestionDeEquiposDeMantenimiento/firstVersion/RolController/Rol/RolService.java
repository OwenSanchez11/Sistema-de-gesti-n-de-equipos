
package gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.RolController.RolDTO.RolCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.RolController.RolDTO.RolUpdateDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    
    public Optional<RolModel> getRolById(Long idRol) {
        return rolRepository.findById(idRol);
    }
    
    public RolModel saveRol(RolCreateDTO request) {
        RolModel rol = new RolModel();
        rol.setName(request.getName());
        return rolRepository.save(rol);
    }
    
    public RolModel updateRol(RolUpdateDTO request, Long idRol) {
        RolModel rol = rolRepository.findById(idRol).get();
        rol.setName(request.getName());
        
        return rolRepository.save(rol);
        
    }
    
    
    public Boolean deleteRol(Long id) {
        try {
            rolRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;

        }
    }
    
    
    
}
