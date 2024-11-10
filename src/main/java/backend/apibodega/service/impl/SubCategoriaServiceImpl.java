package backend.apibodega.service.impl;

import backend.apibodega.exception.CategoriaException;
import backend.apibodega.exception.SubCategoriaException;
import backend.apibodega.mapper.SubCategoriaMapper;
import backend.apibodega.model.dto.request.SubCategoriaRequestDto;
import backend.apibodega.model.dto.response.SubCategoriaResponseDto;
import backend.apibodega.model.entities.Categoria;
import backend.apibodega.model.entities.SubCategoria;
import backend.apibodega.repository.CategoriaRepository;
import backend.apibodega.repository.SubCategoriaRepository;
import backend.apibodega.service.SubCategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

    private final SubCategoriaRepository repository;
    private final CategoriaRepository categoriaRepository;
    private SubCategoriaMapper mapper = SubCategoriaMapper.INSTANCE;

    public SubCategoriaServiceImpl(SubCategoriaRepository repository, CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<SubCategoriaResponseDto> listar() {
        return List.of();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public SubCategoriaResponseDto guardar(SubCategoriaRequestDto dto) {
        Integer idCategoria = dto.idCategoria();
        String nombre = dto.nmobre();

        verificarId(idCategoria);
        verificarNombre(nombre);

        Categoria categoria = obtenerCategoriaPorId(idCategoria);
        SubCategoria subCategoria = SubCategoria.builder()
                .nombre(nombre)
                .categoria(categoria)
                .build();

        SubCategoriaResponseDto response = mapper.toDto(repository.save(subCategoria));

        return response;
    }

    private Categoria obtenerCategoriaPorId(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return categoria.get();
        } else {
            throw new CategoriaException(CategoriaException.CATEGORIA_NO_ENCONTRADA);
        }
    }

    private void verificarId(Integer id) {
        if (id == null || id <= 0) {
            throw new SubCategoriaException(SubCategoriaException.ID_NO_VALIDO);
        }
    }

    private void verificarNombre(String nombre) {
        final int MAXIMO_CARACTERES = 50;
        if (nombre == null || nombre.isBlank() || nombre.isEmpty() || nombre.length() > MAXIMO_CARACTERES) {
            throw new SubCategoriaException(SubCategoriaException.NOMBRE_NO_VALIDO);
        }
    }
}
