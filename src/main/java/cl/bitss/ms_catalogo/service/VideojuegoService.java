package cl.bitss.ms_catalogo.service;
import cl.bitss.ms_catalogo.repository.VideojuegoRepository;
import cl.bitss.ms_catalogo.model.Videojuego;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideojuegoService {
    private final VideojuegoRepository repository;
    public VideojuegoService(VideojuegoRepository repository) {
        this.repository = repository;
    }

    public List<Videojuego> listar() {
        return repository.findAll();
    }

    public Optional<Videojuego> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Videojuego crear(Videojuego videojuego) {
        return repository.save(videojuego);
    }

    public Optional<Videojuego> actualizar(Long id, Videojuego datos) {
        Optional<Videojuego> videojuegoEncontrado = repository.findById(id);
        if (videojuegoEncontrado.isPresent()) {
            Videojuego videojuego = videojuegoEncontrado.get();
            videojuego.setNombre(datos.getNombre());
            videojuego.setCategoria(datos.getCategoria());
            videojuego.setPrecio(datos.getPrecio());
            return Optional.of(repository.save(videojuego));
        }
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Videojuego> buscarPorCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }
}
