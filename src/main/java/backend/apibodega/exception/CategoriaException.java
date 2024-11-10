package backend.apibodega.exception;

public class CategoriaException extends RuntimeException {
    public static String NOMBRE_NO_VALIDO = "NOMBRE_NO_VALIDO";
    public static String ID_NO_VALIDO = "ID_NO_VALIDO";
    public static String CATEGORIA_NO_ENCONTRADA = "CATEGORIA_NO_ENCONTRADA";
    public static String NOMBRE_EXISTE = "NOMBRE_EXISTE";

    public CategoriaException(String message) {
        super(message);
    }
}
