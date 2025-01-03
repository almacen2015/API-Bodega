package backend.apibodega.mapper;

import backend.apibodega.model.dto.request.CategoriaRequestDto;
import backend.apibodega.model.dto.response.CategoriaResponseDto;
import backend.apibodega.model.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    List<CategoriaResponseDto> toDtos(List<Categoria> lista);

    @Mapping(target = "id", ignore = true)
    Categoria toCategoria(CategoriaRequestDto dto);

    CategoriaResponseDto toDto(Categoria entidad);

    Categoria toCategoria(CategoriaResponseDto response);
}
