
package gestionDeEquiposDeMantenimiento.firstVersion.User.DTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreateDTO {
    private String name;
    private String lastName;
    private String email;
    private Boolean active;
    private String documento;
    private String phoneNumber;
    private String cargo;
    private Long idRol;
}
