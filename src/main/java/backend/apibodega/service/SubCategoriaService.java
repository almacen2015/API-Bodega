package backend.apibodega.service;

import backend.apibodega.model.dto.response.SubCategoriaResponseDto;

import java.util.List;

public interface SubCategoriaService {
    List<SubCategoriaResponseDto> listar();
}