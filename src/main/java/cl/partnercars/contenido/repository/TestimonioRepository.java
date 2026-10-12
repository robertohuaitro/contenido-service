package cl.partnercars.contenido.repository;

import cl.partnercars.contenido.model.Testimonio;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestimonioRepository extends JpaRepository<Testimonio, Long> {

    @EntityGraph(attributePaths = "asesor")
    List<Testimonio> findAllByOrderByFechaDescIdDesc();

    @EntityGraph(attributePaths = "asesor")
    List<Testimonio> findByAsesorIdOrderByFechaDescIdDesc(Long asesorId);

    boolean existsByAsesorId(Long asesorId);
}
