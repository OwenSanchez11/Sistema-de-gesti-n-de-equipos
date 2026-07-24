
package gestionDeEquiposDeMantenimiento.firstVersion.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolUpdateDTO;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService {
    
        
    private final RolRepository rolRepository;


    public List<RolModel> getAllRoles() {
        return rolRepository.findAll();
    }
    
    public RolModel getRolById(Long idRol) {
        return rolRepository.findById(idRol)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol con ese ID"));
    }

    
    public void deleteRol(Long id) {
        RolModel rol = rolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el rol con el id"+ id));
        rolRepository.delete(rol);
    }
}
