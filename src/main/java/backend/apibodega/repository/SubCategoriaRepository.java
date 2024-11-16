package backend.apibodega.repository;

import backend.apibodega.model.entities.Categoria;
import backend.apibodega.model.entities.SubCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubCategoriaRepository extends JpaRepository<SubCategoria, Integer> {
    Optional<SubCategoria> findByNombreIgnoreCase(String nombre);

    List<SubCategoria> findAllByCategoriaId(Integer id);

    List<SubCategoria> findByNombreIgnoreCaseAndIdNot(String nombre, Integer id);
}
