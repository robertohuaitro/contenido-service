package cl.partnercars.contenido.service;

import cl.partnercars.contenido.exception.ConflictoException;
import cl.partnercars.contenido.exception.RecursoNoEncontradoException;
import cl.partnercars.contenido.model.Asesor;
import cl.partnercars.contenido.repository.AsesorRepository;
import cl.partnercars.contenido.repository.TestimonioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class AsesorService {

    private final AsesorRepository asesorRepository;
    private final TestimonioRepository testimonioRepository;

    @Transactional(readOnly = true)
    public List<Asesor> listar() {
        return asesorRepository.findAll(Sort.by("nombre"));
    }

    @Transactional(readOnly = true)
    public Asesor obtener(Long id) {
        return asesorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("No existe el asesor con id " + id));
    }

    @Transactional
    public Asesor crear(Asesor asesor) {
        if (asesorRepository.existsByEmailIgnoreCase(asesor.getEmail())) {
            throw new ConflictoException("Ya existe un asesor con el email " + asesor.getEmail());
        }
        asesor.setId(null);
        Asesor creado = asesorRepository.save(asesor);
        log.info("Asesor creado: {} (id {})", creado.getNombre(), creado.getId());
        return creado;
    }

    @Transactional
    public Asesor actualizar(Long id, Asesor datos) {
        Asesor asesor = obtener(id);
        if (asesorRepository.existsByEmailIgnoreCaseAndIdNot(datos.getEmail(), id)) {
            throw new ConflictoException("Ya existe otro asesor con el email " + datos.getEmail());
        }
        asesor.setNombre(datos.getNombre());
        asesor.setCargo(datos.getCargo());
        asesor.setEmail(datos.getEmail());
        asesor.setTelefono(datos.getTelefono());
        Asesor actualizado = asesorRepository.save(asesor);
        log.info("Asesor actualizado: {} (id {})", actualizado.getNombre(), actualizado.getId());
        return actualizado;
    }

    @Transactional
    public void eliminar(Long id) {
        Asesor asesor = obtener(id);
        if (testimonioRepository.existsByAsesorId(id)) {
            throw new ConflictoException("No se puede eliminar al asesor " + asesor.getNombre() + " porque tiene testimonios asociados");
        }
        asesorRepository.delete(asesor);
        log.info("Asesor eliminado: {} (id {})", asesor.getNombre(), id);
    }
}
