
package gestionDeEquiposDeMantenimiento.firstVersion.RolController.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;
    
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    
}
