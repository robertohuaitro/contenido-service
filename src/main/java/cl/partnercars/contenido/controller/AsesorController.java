package cl.partnercars.contenido.controller;

import cl.partnercars.contenido.model.Asesor;
import cl.partnercars.contenido.service.AsesorService;
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
@RequestMapping("/asesores")
@RequiredArgsConstructor
public class AsesorController {

    private final AsesorService asesorService;

    @GetMapping
    public ResponseEntity<List<Asesor>> listar() {
        log.debug("GET /asesores");
        return ResponseEntity.ok(asesorService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asesor> obtener(@PathVariable Long id) {
        log.debug("GET /asesores/{}", id);
        return ResponseEntity.ok(asesorService.obtener(id));
    }

    @PostMapping
    public ResponseEntity<Asesor> crear(@Valid @RequestBody Asesor asesor) {
        log.debug("POST /asesores con email {}", asesor.getEmail());
        Asesor creado = asesorService.crear(asesor);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(ubicacion).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asesor> actualizar(@PathVariable Long id, @Valid @RequestBody Asesor asesor) {
        log.debug("PUT /asesores/{}", id);
        return ResponseEntity.ok(asesorService.actualizar(id, asesor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        log.debug("DELETE /asesores/{}", id);
        asesorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
