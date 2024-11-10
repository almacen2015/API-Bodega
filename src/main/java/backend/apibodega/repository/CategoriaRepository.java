package backend.apibodega.repository;

import backend.apibodega.model.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByNombreIgnoreCase(String nombre);

    List<Categoria> findByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
}
