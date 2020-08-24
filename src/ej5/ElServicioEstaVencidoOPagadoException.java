package ej5;

public class ElServicioEstaVencidoOPagadoException extends RuntimeException {
    private static final String ERROR_MSG = "El servicio esta vencido o ya esta pagado";

    public ElServicioEstaVencidoOPagadoException() {
    }

    @Override
    public String getMessage() {
        return ERROR_MSG;
    }

}