package ej5;

public class ServicioYaSePagoException extends RuntimeException {
    private static final String ERROR_MSG = "El servicio ya se encontraba pagado anteriormente";

    public ServicioYaSePagoException () {
    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
