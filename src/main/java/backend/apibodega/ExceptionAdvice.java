package backend.apibodega;

import backend.apibodega.exception.CategoriaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CategoriaException.class)
    public ResponseEntity<?> handleCategoriaException(CategoriaException e) {
        if (Objects.equals(e.getMessage(), CategoriaException.NOMBRE_NO_VALIDO)
                || Objects.equals(e.getMessage(), CategoriaException.ID_NO_VALIDO)) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
