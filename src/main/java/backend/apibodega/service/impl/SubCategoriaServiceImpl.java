package backend.apibodega.service.impl;

import backend.apibodega.model.dto.response.SubCategoriaResponseDto;
import backend.apibodega.repository.SubCategoriaRepository;
import backend.apibodega.service.SubCategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoriaServiceImpl implements SubCategoriaService {

    private final SubCategoriaRepository repository;

    public SubCategoriaServiceImpl(SubCategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SubCategoriaResponseDto> listar() {
        return List.of();
    }
}
