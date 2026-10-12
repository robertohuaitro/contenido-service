package cl.partnercars.contenido.repository;

import cl.partnercars.contenido.model.Asesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsesorRepository extends JpaRepository<Asesor, Long> {

    boolean existsByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
