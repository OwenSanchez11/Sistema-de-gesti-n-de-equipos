
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String status;
    @Column
    private String location;
    @Column
    private Boolean active; 
    
    
}
