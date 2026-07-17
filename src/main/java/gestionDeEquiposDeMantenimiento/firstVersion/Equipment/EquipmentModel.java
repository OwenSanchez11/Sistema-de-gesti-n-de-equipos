
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipment;
    @Column(unique = true, nullable = false)
    private int codeInventory;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String brand;
    @Column
    private String model;
    @Column(unique = true, nullable = false)
    private int seriesNum;
    @Column
    @Enumerated(EnumType.STRING)
    private EquipmentStatus status;
    @Column
    private String location;
    @Column
    private Boolean active; 
    
    
}
