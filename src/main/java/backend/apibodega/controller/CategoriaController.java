package backend.apibodega.controller;

import backend.apibodega.model.dto.response.CategoriaResponseDto;
import backend.apibodega.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/categoria")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> listar() {
        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }
}
