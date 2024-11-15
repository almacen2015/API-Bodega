package backend.apibodega.controller;

import backend.apibodega.model.dto.request.CategoriaRequestDto;
import backend.apibodega.model.dto.request.SubCategoriaRequestDto;
import backend.apibodega.model.dto.response.CategoriaResponseDto;
import backend.apibodega.model.dto.response.SubCategoriaResponseDto;
import backend.apibodega.service.SubCategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subcategoria")
public class SubCategoriaController {

    private final SubCategoriaService service;

    public SubCategoriaController(SubCategoriaService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todas las SubCategoria", description = "Lista todas las SubCategorias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "array", example = "List", implementation = List.class)))})
    @GetMapping
    public ResponseEntity<List<SubCategoriaResponseDto>> listar() {
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @Operation(summary = "Guarda una SubCategoria", description = "Guarda una SubCategoria")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(type = "array", implementation = SubCategoriaRequestDto.class)))})
    @PostMapping
    public ResponseEntity<SubCategoriaResponseDto> guardar(@RequestBody SubCategoriaRequestDto dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }
}
