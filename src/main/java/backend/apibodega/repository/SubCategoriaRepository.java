package backend.apibodega.repository;

import backend.apibodega.model.entities.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer> {
    Optional<SubCategoria> findByNombreIgnoreCase(String nombre);
}
