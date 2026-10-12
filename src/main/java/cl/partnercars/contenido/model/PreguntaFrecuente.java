package cl.partnercars.contenido.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "preguntas_frecuentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PreguntaFrecuente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pregunta")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "La pregunta es obligatoria")
    @Size(max = 255, message = "La pregunta no puede superar los 255 caracteres")
    @Column(name = "pregunta", nullable = false)
    private String pregunta;

    @NotBlank(message = "La respuesta es obligatoria")
    @Size(max = 2000, message = "La respuesta no puede superar los 2000 caracteres")
    @Column(name = "respuesta", nullable = false, length = 2000)
    private String respuesta;

    @NotNull(message = "El orden es obligatorio")
    @PositiveOrZero(message = "El orden no puede ser negativo")
    @Column(name = "orden", nullable = false)
    private Integer orden;
}
