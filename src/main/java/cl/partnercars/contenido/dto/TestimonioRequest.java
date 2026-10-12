package cl.partnercars.contenido.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestimonioRequest {

    @NotNull(message = "El asesor es obligatorio")
    private Long asesorId;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    private String nombreCliente;

    @Size(max = 150, message = "El vehículo no puede superar los 150 caracteres")
    private String vehiculoComprado;

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer calificacion;

    @NotBlank(message = "El comentario es obligatorio")
    @Size(max = 1000, message = "El comentario no puede superar los 1000 caracteres")
    private String comentario;

    @PastOrPresent(message = "La fecha no puede estar en el futuro")
    private LocalDate fecha;
}
