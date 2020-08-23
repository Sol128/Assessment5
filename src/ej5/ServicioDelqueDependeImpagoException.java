package ej5;

public class ServicioDelqueDependeImpagoException extends RuntimeException {
    private static final String ERROR_MSG = "El servicio del que depende se encuentra impago";

    public ServicioDelqueDependeImpagoException() {
    }

    @Override
    public String getMessage() {
        return ERROR_MSG;
    }

}
