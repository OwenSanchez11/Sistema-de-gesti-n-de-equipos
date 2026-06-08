
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private String email;
    private Boolean active;
    private String phoneNumber;
    private String cargo;
    private Long idRol;
}
