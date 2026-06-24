
package gestionDeEquiposDeMantenimiento.firstVersion.Rol;

import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.BusinessRuleException;
import gestionDeEquiposDeMantenimiento.firstVersion.Exceptions.ResourceNotFoundException;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolRepository;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolCreateDTO;
import gestionDeEquiposDeMantenimiento.firstVersion.RolDTO.RolUpdateDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
    
    public RolModel getRolById(Long idRol) {
        return rolRepository.findById(idRol).orElseThrow(() -> new ResourceNotFoundException("No se encontró un rol con ese ID"));
    }
    
    public RolModel saveRol(RolCreateDTO request) {
        
        boolean rolExists = rolRepository.existsByName(request.getName());
        
        if (rolExists) {
            throw new BusinessRuleException("Ya existe ese rol");
        }
        
        RolModel rol = new RolModel();
        rol.setName(request.getName().toUpperCase());
        
        return rolRepository.save(rol);
    }
    
    public RolModel updateRol(RolUpdateDTO request, Long idRol) {
        RolModel rol = rolRepository.findById(idRol).get();
        rol.setName(request.getName());
        
        return rolRepository.save(rol);
        
    }
    
    
    public void deleteRol(Long id) {
        RolModel rol = rolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el rol con el id"+ id));
        rolRepository.delete(rol);
    }
}
