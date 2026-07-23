
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO;

import gestionDeEquiposDeMantenimiento.firstVersion.Equipment.EquipmentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "EquipmentCreateDTO",
    description = "Modelo de datos requerido para registrar un nuevo equipo en el inventario"
)
public class EquipmentCreateDTO {

    @Schema(
        description = "Nombre comercial o categoría principal del equipo",
        example = "Impresora Láser",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Equipment name cannot be empty")
    private String name;
    
    @Schema(
        description = "Código interno único asignado para el control de inventario",
        example = "45012",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Code Inventory cannot be empty")
    @Positive(message = "Inventory code must be greater than 0")
    private Integer codeInventory;
    
    @Schema(
        description = "Detalles técnicos o especificaciones adicionales del equipo",
        example = "Monocromática, conexión Wi-Fi, capacidad de 250 hojas.",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Description equipment cannot be empty")
    private String description;
    
    @Schema(
        description = "Marca del fabricante",
        example = "HP",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Brand cannot be empty")
    private String brand;

    @Schema(
        description = "Modelo específico asignado por el fabricante",
        example = "LaserJet Pro M404dw",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Model cannot be empty")
    private String model;
    
    @Schema(
        description = "Número de serie de fábrica del dispositivo",
        example = "7891011",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Series number cannot be empty")
    @Positive(message = "Series number must be greater than 0")
    private Integer seriesNum;

    @Schema(
        description = "Ubicación física actual o almacén donde se resguarda el equipo",
        example = "Laboratorio de Sistemas - Aula 302",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Location cannot be empty")
    private String location;
}
