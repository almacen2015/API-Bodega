package backend.apibodega.service.impl;

import backend.apibodega.exception.CategoriaException;
import backend.apibodega.mapper.CategoriaMapper;
import backend.apibodega.model.dto.request.CategoriaRequestDto;
import backend.apibodega.model.dto.response.CategoriaResponseDto;
import backend.apibodega.model.entities.Categoria;
import backend.apibodega.repository.CategoriaRepository;
import backend.apibodega.service.CategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper = CategoriaMapper.INSTANCE;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoriaResponseDto> listar() {
        List<Categoria> categorias = repository.findAll();
        List<CategoriaResponseDto> categoriasResponse = mapper.toDtos(categorias);
        return categoriasResponse;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public CategoriaResponseDto guardar(CategoriaRequestDto dto) {
        String nombre = dto.nombre();
        verificarNombre(nombre);
        verificarExistenciaNombre(nombre);
        Categoria categoria = mapper.toCategoria(dto);
        CategoriaResponseDto response = mapper.toDto(repository.save(categoria));
        return response;
    }

    @Override
    public CategoriaResponseDto actualizar(CategoriaRequestDto dto, Integer id) {
        String nombre = dto.nombre();
        verificarId(id);
        verificarNombre(nombre);

        Optional<Categoria> categoriaEntity = repository.findById(id);
        if (categoriaEntity.isEmpty()) {
            throw new CategoriaException(CategoriaException.CATEGORIA_NO_ENCONTRADA);
        }

        List<Categoria> categoriaEncontradas = repository.findByNombreIgnoreCaseAndIdNot(nombre, id);
        if (!categoriaEncontradas.isEmpty()) {
            throw new CategoriaException(CategoriaException.NOMBRE_EXISTE);
        }

        Categoria categoria = categoriaEntity.get();
        categoria.setNombre(nombre);
        CategoriaResponseDto response = mapper.toDto(repository.save(categoria));
        return response;
    }

    @Override
    public CategoriaResponseDto obtenerPorId(Integer id) {
        verificarId(id);
        Optional<Categoria> categoriaEncontrada = repository.findById(id);
        if (categoriaEncontrada.isPresent()) {
            CategoriaResponseDto response = mapper.toDto(categoriaEncontrada.get());
            return response;
        } else {
            return null;
        }
    }

    @Override
    public CategoriaResponseDto obtenerPorNombre(String nombre) {
        verificarNombre(nombre);
        Optional<Categoria> categoriaEncontrada = repository.findByNombreIgnoreCase(nombre);
        if (categoriaEncontrada.isPresent()) {
            CategoriaResponseDto response = mapper.toDto(categoriaEncontrada.get());
            return response;
        } else {
            return null;
        }
    }

    private void verificarExistenciaNombre(String nombre) {
        Optional<Categoria> nombreEncontrado = repository.findByNombreIgnoreCase(nombre);
        if (nombreEncontrado.isPresent()) {
            throw new CategoriaException(CategoriaException.NOMBRE_EXISTE);
        }
    }

    private void verificarId(Integer id) {
        if (id == null || id <= 0) {
            throw new CategoriaException(CategoriaException.ID_NO_VALIDO);
        }
    }

    private void verificarNombre(String nombre) {
        final int MAXIMO_CARACTERES = 50;
        if (nombre == null || nombre.isBlank() || nombre.isEmpty() || nombre.length() > MAXIMO_CARACTERES) {
            throw new CategoriaException(CategoriaException.NOMBRE_NO_VALIDO);
        }
    }
}
