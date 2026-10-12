package cl.partnercars.contenido.repository;

import cl.partnercars.contenido.model.PreguntaFrecuente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntaFrecuenteRepository extends JpaRepository<PreguntaFrecuente, Long> {

    List<PreguntaFrecuente> findAllByOrderByOrdenAscIdAsc();
}
