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
        Categoria categoria = mapper.toCategoria(dto);
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
