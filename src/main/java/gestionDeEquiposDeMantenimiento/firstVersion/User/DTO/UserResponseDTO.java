
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter

public class UserResponseDTO {
    private Long userId;
    private String name;
    private String lastName;
    private Boolean active;
    private String cargo;

    public UserResponseDTO(Long userId, String name, String lastName, Boolean active, String cargo) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
        this.cargo = cargo;
    }
    
    
}
