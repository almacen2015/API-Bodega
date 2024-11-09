package backend.apibodega.service;

import backend.apibodega.model.dto.request.CategoriaRequestDto;
import backend.apibodega.model.dto.response.CategoriaResponseDto;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDto> listar();

    CategoriaResponseDto guardar(CategoriaRequestDto dto);
}
