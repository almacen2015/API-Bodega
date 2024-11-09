package backend.apibodega.exception;

public class CategoriaException extends RuntimeException {
    public static String NOMBRE_NO_VALIDO = "NOMBRE_NO_VALIDO";

    public CategoriaException(String message) {
        super(message);
    }
}
