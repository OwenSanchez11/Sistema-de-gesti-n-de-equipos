
package gestionDeEquiposDeMantenimiento.firstVersion.Equipment.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(
    name = "EquipmentUpdateDTO",
    description = "Modelo de datos utilizado para actualizar el estado o la disponibilidad de un equipo existente"
)
public class EquipmentUpdateDTO {

    @Schema(
        description = "Define si el equipo está activo/habilitado en el sistema para nuevos préstamos",
        example = "true/false")
    private Boolean active;

    @Schema(
        description = "Nuevo estado físico u operativo del equipo",
        example = "MANTENIMIENTO, ACTIVO, EN PROGRESO")
    private String status;
}