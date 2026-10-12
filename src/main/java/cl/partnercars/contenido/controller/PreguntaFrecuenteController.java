package cl.partnercars.contenido.controller;

import cl.partnercars.contenido.model.PreguntaFrecuente;
import cl.partnercars.contenido.service.PreguntaFrecuenteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/preguntas-frecuentes")
@RequiredArgsConstructor
public class PreguntaFrecuenteController {

    private final PreguntaFrecuenteService preguntaFrecuenteService;

    @GetMapping
    public ResponseEntity<List<PreguntaFrecuente>> listar() {
        log.debug("GET /preguntas-frecuentes");
        return ResponseEntity.ok(preguntaFrecuenteService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntaFrecuente> obtener(@PathVariable Long id) {
        log.debug("GET /preguntas-frecuentes/{}", id);
        return ResponseEntity.ok(preguntaFrecuenteService.obtener(id));
    }

    @PostMapping
    public ResponseEntity<PreguntaFrecuente> crear(@Valid @RequestBody PreguntaFrecuente pregunta) {
        log.debug("POST /preguntas-frecuentes en la posición {}", pregunta.getOrden());
        PreguntaFrecuente creada = preguntaFrecuenteService.crear(pregunta);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creada.getId())
                .toUri();
        return ResponseEntity.created(ubicacion).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreguntaFrecuente> actualizar(@PathVariable Long id, @Valid @RequestBody PreguntaFrecuente pregunta) {
        log.debug("PUT /preguntas-frecuentes/{}", id);
        return ResponseEntity.ok(preguntaFrecuenteService.actualizar(id, pregunta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.debug("DELETE /preguntas-frecuentes/{}", id);
        preguntaFrecuenteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
