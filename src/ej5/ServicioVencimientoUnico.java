package ej5;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServicioVencimientoUnico extends Servicio {


    public ServicioVencimientoUnico(String descripcion, Double monto, Moneda moneda, LocalDate vto) {
        super(descripcion, monto, moneda, vto);
    }

    public ServicioVencimientoUnico(String descripcion, Double monto, Moneda moneda, LocalDate vto, ArrayList<Servicio> servicios) {
        super(descripcion, monto, moneda, vto, servicios);
    }

    @Override
    public void pagar() {

        LocalDate hoy = LocalDate.now();

        if (hoy.isAfter(this.vto)){
            this.estado = Estado.VENCIDO;
        }

        super.evaluarPago();
    }


    @Override
    protected Double getTotal() {
        return getMonto();
    }
}
