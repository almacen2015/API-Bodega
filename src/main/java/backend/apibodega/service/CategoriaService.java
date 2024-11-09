package backend.apibodega.service;

import backend.apibodega.model.dto.response.CategoriaResponseDto;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDto> listar();

    CategoriaResponseDto guardar(CategoriaResponseDto dto);
}
