package cl.partnercars.contenido.controller;

import cl.partnercars.contenido.dto.TestimonioRequest;
import cl.partnercars.contenido.model.Testimonio;
import cl.partnercars.contenido.service.TestimonioService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/testimonios")
@RequiredArgsConstructor
public class TestimonioController {

    private final TestimonioService testimonioService;

    @GetMapping
    public ResponseEntity<List<Testimonio>> listar(@RequestParam(required = false) Long asesorId) {
        log.debug("GET /testimonios con asesorId={}", asesorId);
        return ResponseEntity.ok(testimonioService.listar(asesorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Testimonio> obtener(@PathVariable Long id) {
        log.debug("GET /testimonios/{}", id);
        return ResponseEntity.ok(testimonioService.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Testimonio> crear(@Valid @RequestBody TestimonioRequest request) {
        log.debug("POST /testimonios para el asesor {}", request.getAsesorId());
        Testimonio creado = testimonioService.crear(request);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(ubicacion).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Testimonio> actualizar(@PathVariable Long id, @Valid @RequestBody TestimonioRequest request) {
        log.debug("PUT /testimonios/{}", id);
        return ResponseEntity.ok(testimonioService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.debug("DELETE /testimonios/{}", id);
        testimonioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
