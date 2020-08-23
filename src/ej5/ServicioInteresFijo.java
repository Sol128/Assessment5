package ej5;

import java.time.LocalDate;
import java.util.ArrayList;

public class ServicioInteresFijo extends ServicioDobleVencimiento {

    private static Double tasaDeInteresFija = new Double(0.10);

    public ServicioInteresFijo(String descripcion, Double monto, Moneda moneda, LocalDate vto, LocalDate segundoVto) {
        super(descripcion, monto, moneda, vto, segundoVto);
    }

    public ServicioInteresFijo(String descripcion, Double monto, Moneda moneda, LocalDate vto, ArrayList<Servicio> servicios, LocalDate segundoVto) {
        super(descripcion, monto, moneda, vto, servicios, segundoVto);
    }

    @Override
    protected Double getTotal() {
        LocalDate hoy = LocalDate.now();

        if (hoy.isAfter(this.vto)) {
            return (super.getMonto() + (super.getMonto() * tasaDeInteresFija));
        }

        return super.getMonto();
    }
}