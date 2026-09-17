package cl.bitss.ms_catalogo.controller;
import cl.bitss.ms_catalogo.model.Videojuego;
import cl.bitss.ms_catalogo.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/videojuegos")
public class VideojuegoController {
    private final VideojuegoService service;
    public VideojuegoController(VideojuegoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Videojuego> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Videojuego> crear(@Valid @RequestBody Videojuego videojuego) {
        return ResponseEntity.ok(service.crear(videojuego));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Videojuego> actualizar(@PathVariable Long id, @Valid @RequestBody Videojuego videojuego) {
        return service.actualizar(id, videojuego)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{categoria}")
    public List<Videojuego> buscarPorCategoria(@PathVariable String categoria) {
        return service.buscarPorCategoria(categoria);
    }
}