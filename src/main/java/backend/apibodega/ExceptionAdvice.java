package backend.apibodega;

import backend.apibodega.exception.CategoriaException;
import backend.apibodega.exception.SubCategoriaException;
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
                || Objects.equals(e.getMessage(), CategoriaException.ID_NO_VALIDO)
                || Objects.equals(e.getMessage(), CategoriaException.NOMBRE_EXISTE)) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(SubCategoriaException.class)
    public ResponseEntity<?> handleSubCategoriaException(SubCategoriaException e) {
        if (Objects.equals(e.getMessage(), SubCategoriaException.ID_NO_VALIDO) ||
                Objects.equals(e.getMessage(), SubCategoriaException.NOMBRE_EXISTE) ||
                Objects.equals(e.getMessage(), SubCategoriaException.NOMBRE_NO_VALIDO)) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
