package cl.partnercars.contenido.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "asesores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Asesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asesor")
    @EqualsAndHashCode.Include
    private Long id;

    @NotBlank(message = "El nombre del asesor es obligatorio")
    @Size(max = 150, message = "El nombre no puede superar los 150 caracteres")
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Size(max = 100, message = "El cargo no puede superar los 100 caracteres")
    @Column(name = "cargo", length = 100)
    private String cargo;

    @NotBlank(message = "El email del asesor es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Size(max = 120, message = "El email no puede superar los 120 caracteres")
    @Column(name = "email", nullable = false, unique = true, length = 120)
    private String email;

    @Pattern(regexp = "^\\+?[0-9 ]{8,15}$", message = "El teléfono debe tener entre 8 y 15 dígitos, con + opcional")
    @Column(name = "telefono", length = 20)
    private String telefono;

    @JsonIgnore
    @OneToMany(mappedBy = "asesor")
    private List<Testimonio> testimonios;
}
