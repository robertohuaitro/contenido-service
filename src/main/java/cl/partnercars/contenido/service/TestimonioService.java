package cl.partnercars.contenido.service;

import cl.partnercars.contenido.dto.TestimonioRequest;
import cl.partnercars.contenido.exception.RecursoNoEncontradoException;
import cl.partnercars.contenido.model.Asesor;
import cl.partnercars.contenido.model.Testimonio;
import cl.partnercars.contenido.repository.TestimonioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TestimonioService {

    private final TestimonioRepository testimonioRepository;
    private final AsesorService asesorService;

    @Transactional(readOnly = true)
    public List<Testimonio> listar(Long asesorId) {
        if (asesorId != null) {
            return testimonioRepository.findByAsesorIdOrderByFechaDescIdDesc(asesorId);
        }
        return testimonioRepository.findAllByOrderByFechaDescIdDesc();
    }

    @Transactional(readOnly = true)
    public Testimonio obtener(Long id) {
        return testimonioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el testimonio con id " + id));
    }

    @Transactional
    public Testimonio crear(TestimonioRequest request) {
        Asesor asesor = asesorService.obtener(request.getAsesorId());
        Testimonio testimonio = Testimonio.builder().asesor(asesor).build();
        copiarDatos(request, testimonio);
        Testimonio creado = testimonioRepository.save(testimonio);
        log.info("Testimonio {} creado para el asesor {} con {} estrellas", creado.getId(), asesor.getNombre(), creado.getCalificacion());
        return creado;
    }

    @Transactional
    public Testimonio actualizar(Long id, TestimonioRequest request) {
        Testimonio testimonio = obtener(id);
        testimonio.setAsesor(asesorService.obtener(request.getAsesorId()));
        copiarDatos(request, testimonio);
        Testimonio actualizado = testimonioRepository.save(testimonio);
        log.info("Testimonio {} actualizado", actualizado.getId());
        return actualizado;
    }

    @Transactional
    public void eliminar(Long id) {
        Testimonio testimonio = obtener(id);
        testimonioRepository.delete(testimonio);
        log.info("Testimonio {} eliminado", id);
    }

    private void copiarDatos(TestimonioRequest request, Testimonio testimonio) {
        testimonio.setNombreCliente(request.getNombreCliente());
        testimonio.setVehiculoComprado(request.getVehiculoComprado());
        testimonio.setCalificacion(request.getCalificacion());
        testimonio.setComentario(request.getComentario());
        if (request.getFecha() != null) {
            testimonio.setFecha(request.getFecha());
        }
    }
}
