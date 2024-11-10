package backend.apibodega.controller;

import backend.apibodega.model.dto.request.CategoriaRequestDto;
import backend.apibodega.model.dto.response.CategoriaResponseDto;
import backend.apibodega.service.CategoriaService;
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
@RequestMapping("/api/categoria")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @Operation(summary = "Lista todas las categoria", description = "Lista todas las categorias")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "array", implementation = List.class)))})
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listar() {
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @Operation(summary = "Guarda una categoria", description = "Guarda una categoria")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(type = "array", implementation = CategoriaRequestDto.class)))})
    @PostMapping
    public ResponseEntity<CategoriaResponseDto> guardar(@RequestBody CategoriaRequestDto dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene una categoria por Id", description = "Obtiene una categoria por Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "array", implementation = CategoriaResponseDto.class)))})
    @GetMapping("/obtener-id")
    public ResponseEntity<CategoriaResponseDto> obtenerPorId(@RequestParam Integer id) {
        return new ResponseEntity<>(service.obtenerPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una categoria por nombre", description = "Obtiene una categoria por nombre")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(type = "array", implementation = CategoriaResponseDto.class)))})
    @GetMapping("/obtener-nombre")
    public ResponseEntity<CategoriaResponseDto> obtenerPorNombre(@RequestParam String nombre) {
        return new ResponseEntity<>(service.obtenerPorNombre(nombre), HttpStatus.OK);
    }
}
