
package gestionDeEquiposDeMantenimiento.firstVersion.User;

import gestionDeEquiposDeMantenimiento.firstVersion.Rol.RolModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column
    private String name;
    @Column
    private String lastName;
    @Column
    private String documento;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private RolModel rol;
    @Column
    private String cargo;

}
