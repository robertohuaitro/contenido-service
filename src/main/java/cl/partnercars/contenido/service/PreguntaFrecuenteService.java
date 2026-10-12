package cl.partnercars.contenido.service;

import cl.partnercars.contenido.exception.RecursoNoEncontradoException;
import cl.partnercars.contenido.model.PreguntaFrecuente;
import cl.partnercars.contenido.repository.PreguntaFrecuenteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PreguntaFrecuenteService {

    private final PreguntaFrecuenteRepository preguntaFrecuenteRepository;

    @Transactional(readOnly = true)
    public List<PreguntaFrecuente> listar() {
        return preguntaFrecuenteRepository.findAllByOrderByOrdenAscIdAsc();
    }

    @Transactional(readOnly = true)
    public PreguntaFrecuente obtener(Long id) {
        return preguntaFrecuenteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe la pregunta frecuente con id " + id));
    }

    @Transactional
    public PreguntaFrecuente crear(PreguntaFrecuente pregunta) {
        pregunta.setId(null);
        PreguntaFrecuente creada = preguntaFrecuenteRepository.save(pregunta);
        log.info("Pregunta frecuente {} creada en la posición {}", creada.getId(), creada.getOrden());
        return creada;
    }

    @Transactional
    public PreguntaFrecuente actualizar(Long id, PreguntaFrecuente datos) {
        PreguntaFrecuente pregunta = obtener(id);
        pregunta.setPregunta(datos.getPregunta());
        pregunta.setRespuesta(datos.getRespuesta());
        pregunta.setOrden(datos.getOrden());
        PreguntaFrecuente actualizada = preguntaFrecuenteRepository.save(pregunta);
        log.info("Pregunta frecuente {} actualizada", actualizada.getId());
        return actualizada;
    }

    @Transactional
    public void eliminar(Long id) {
        PreguntaFrecuente pregunta = obtener(id);
        preguntaFrecuenteRepository.delete(pregunta);
        log.info("Pregunta frecuente {} eliminada", id);
    }
}
