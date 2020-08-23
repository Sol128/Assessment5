package ej5;

public class NoSePuedePagarServicioVencidoException extends RuntimeException {
    private static final String ERROR_MSG = "El servicio esta vencido, no se puede realizar el pago";

    public NoSePuedePagarServicioVencidoException () {
    }

    @Override
    public String getMessage () {
        return ERROR_MSG;
    }
}
