
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment;

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
@Table(name = "Equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipment;
    private int codeInventory;
    private String name;
    private String description;
    private String brand;
    private String model;
    private int seriesNum;
    private String status;
    private String location;
    private Boolean active; 
    
    
}
