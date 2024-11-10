package backend.apibodega.controller;

import backend.apibodega.service.SubCategoriaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategoria")
public class SubCategoriaController {

    private final SubCategoriaService service;

    public SubCategoriaController(SubCategoriaService service) {
        this.service = service;
    }
}
