package cl.partnercars.contenido.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "testimonios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Testimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_testimonio")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_asesor", nullable = false)
    private Asesor asesor;

    @Column(name = "nombre_cliente", nullable = false, length = 150)
    private String nombreCliente;

    @Column(name = "vehiculo_comprado", length = 150)
    private String vehiculoComprado;

    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    @Column(name = "comentario", nullable = false, length = 1000)
    private String comentario;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @PrePersist
    void alCrear() {
        if (fecha == null) {
            fecha = LocalDate.now();
        }
    }
}
