package backend.apibodega.mapper;

import backend.apibodega.model.dto.response.SubCategoriaResponseDto;
import backend.apibodega.model.entities.SubCategoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubCategoriaMapper {
    SubCategoriaMapper INSTANCE = Mappers.getMapper(SubCategoriaMapper.class);

    List<SubCategoriaResponseDto> toDtos(List<SubCategoria> lista);

    SubCategoriaResponseDto toDto(SubCategoria entidad);
}
