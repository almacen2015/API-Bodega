package backend.apibodega.service.impl;

import backend.apibodega.exception.SubCategoriaException;
import backend.apibodega.mapper.CategoriaMapper;
import backend.apibodega.mapper.SubCategoriaMapper;
import backend.apibodega.model.dto.request.SubCategoriaRequestDto;
import backend.apibodega.model.dto.response.SubCategoriaResponseDto;
import backend.apibodega.model.entities.Categoria;
import backend.apibodega.model.entities.SubCategoria;
import backend.apibodega.repository.SubCategoriaRepository;
import backend.apibodega.service.CategoriaService;
import backend.apibodega.service.SubCategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

    private final SubCategoriaRepository repository;
    private final CategoriaService categoriaService;

    private final SubCategoriaMapper mapper = SubCategoriaMapper.INSTANCE;
    private final CategoriaMapper mapperCategoria = CategoriaMapper.INSTANCE;

    public SubCategoriaServiceImpl(SubCategoriaRepository repository, CategoriaService categoriaService) {
        this.repository = repository;
        this.categoriaService = categoriaService;
    }

    @Override
    public List<SubCategoriaResponseDto> listar() {
        List<SubCategoria> subCategorias = repository.findAll();
        return mapper.toDtos(subCategorias);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public SubCategoriaResponseDto guardar(SubCategoriaRequestDto dto) {
        Integer idCategoria = dto.idCategoria();
        String nombre = dto.nmobre();

        verificarId(idCategoria);
        verificarNombre(nombre);
        SubCategoriaResponseDto nombreEncontrado = obtenerPorNombre(nombre);
        if (nombreEncontrado != null) {
            throw new SubCategoriaException(SubCategoriaException.NOMBRE_EXISTE);
        }

        Categoria categoria = mapperCategoria.toCategoria(categoriaService.obtenerPorId(idCategoria));
        SubCategoria subCategoria = SubCategoria.builder()
                .nombre(nombre)
                .categoria(categoria)
                .build();

        SubCategoriaResponseDto response = mapper.toDto(repository.save(subCategoria));

        return response;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public SubCategoriaResponseDto actualizar(SubCategoriaRequestDto dto, Integer id) {
        Integer idCategoria = dto.idCategoria();
        String nombre = dto.nmobre();

        verificarId(idCategoria);
        verificarNombre(nombre);

        List<SubCategoria> nombresEncontrados = repository.findByNombreIgnoreCaseAndIdNot(nombre, id);
        if (!nombresEncontrados.isEmpty()) {
            throw new SubCategoriaException(SubCategoriaException.NOMBRE_EXISTE);
        }

        Optional<SubCategoria> subCategoriaActualizar = repository.findById(id);
        if (subCategoriaActualizar.isPresent()) {
            SubCategoria subCategoria = subCategoriaActualizar.get();
            Categoria categoria = mapperCategoria.toCategoria(categoriaService.obtenerPorId(idCategoria));

            subCategoria.setNombre(nombre);
            subCategoria.setCategoria(categoria);

            SubCategoriaResponseDto response = mapper.toDto(repository.save(subCategoria));
            return response;
        }

        throw new SubCategoriaException(SubCategoriaException.SUBCATEGORIA_NO_ENCONTRADA);

    }

    @Override
    public SubCategoriaResponseDto obtenerPorNombre(String nombre) {
        verificarNombre(nombre);
        Optional<SubCategoria> subCtageoriaEncontrada = repository.findByNombreIgnoreCase(nombre);
        if (subCtageoriaEncontrada.isPresent()) {
            SubCategoriaResponseDto response = mapper.toDto(subCtageoriaEncontrada.get());
            return response;
        }
        return null;
    }

    @Override
    public SubCategoriaResponseDto obtenerPorId(Integer id) {
        verificarId(id);
        Optional<SubCategoria> subCategoriaEncontrada = repository.findById(id);
        if (subCategoriaEncontrada.isPresent()) {
            SubCategoriaResponseDto response = mapper.toDto(subCategoriaEncontrada.get());
            return response;
        }
        return null;
    }

    @Override
    public List<SubCategoriaResponseDto> listarPorIdCategoria(Integer id) {
        verificarId(id);
        List<SubCategoria> subCategorias = repository.findAllByCategoriaId(id);
        List<SubCategoriaResponseDto> response = mapper.toDtos(subCategorias);
        return response;
    }

    private void verificarId(Integer id) {
        if (id == null || id <= 0) {
            throw new SubCategoriaException(SubCategoriaException.ID_NO_VALIDO);
        }
    }

    private void verificarNombre(String nombre) {
        final int MAXIMO_CARACTERES = 50;
        if (nombre == null || nombre.isBlank() || nombre.length() > MAXIMO_CARACTERES) {
            throw new SubCategoriaException(SubCategoriaException.NOMBRE_NO_VALIDO);
        }
    }
}
