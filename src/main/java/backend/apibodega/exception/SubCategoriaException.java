package backend.apibodega.exception;

public class SubCategoriaException extends RuntimeException {
    public static final String NOMBRE_NO_VALIDO = "NOMBRE_NO_VALIDO";
    public static final String ID_NO_VALIDO = "ID_NO_VALIDO";

    public SubCategoriaException(String message) {
        super(message);
    }
}
