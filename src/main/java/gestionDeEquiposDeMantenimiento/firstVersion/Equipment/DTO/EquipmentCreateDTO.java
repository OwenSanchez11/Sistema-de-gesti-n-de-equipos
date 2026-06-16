
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentCreateDTO {
   @NotBlank(message = "Equipment name cannot be empty")
    private String name;
   
   @NotNull(message = "Code Inventory cannot be empty")
   @Positive(message = "Inventory code must be greater than 0")
    private Integer codeInventory;
   
   @NotBlank(message = "Description equipment cannot be empty")
    private String description;
   
   @NotBlank(message = "Brand cannot be empty")
    private String brand;
   @NotBlank(message = "Model cannot be empty")
    private String model;
   
   @NotNull(message = "Series number cannot be empty")
   @Positive(message = "Series number must be greater than 0")
    private Integer seriesNum;
   @NotBlank(message = "status cannot be empty")
    private String status;
   @NotBlank(message = "Location cannot be empty")
    private String location;

}
