package backend.apibodega.service.impl;

import backend.apibodega.mapper.CategoriaMapper;
import backend.apibodega.model.dto.response.CategoriaResponseDto;
import backend.apibodega.model.entities.Categoria;
import backend.apibodega.repository.CategoriaRepository;
import backend.apibodega.service.CategoriaService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import javax.naming.directory.InvalidAttributesException;
import java.util.List;

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
    public CategoriaResponseDto guardar(CategoriaResponseDto dto) {
        String nombre = dto.nombre();
        verificarNombre(nombre);
        Categoria categoria = mapper.toCategoria(dto);
        CategoriaResponseDto response = mapper.toDto(repository.save(categoria));
        return response;
    }

    private void verificarNombre(String nombre) {
        if (nombre == null || nombre.isBlank() || nombre.isEmpty() || nombre.length() > 50) {
            throw new InvalidAttributesException("Nombre invalido");
        }
    }
}
