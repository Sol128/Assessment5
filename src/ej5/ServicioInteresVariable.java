package ej5;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ServicioInteresVariable extends ServicioDobleVencimiento {

    Double tasaDeInteres;

    public ServicioInteresVariable(String descripcion, Double monto, Moneda moneda, LocalDate vto, LocalDate segundoVto, Double tasaDeInteres) {
        super(descripcion, monto, moneda, vto, segundoVto);
        this.tasaDeInteres = tasaDeInteres;
    }

    public ServicioInteresVariable(String descripcion, Double monto, Moneda moneda, LocalDate vto, ArrayList<Servicio> servicios, LocalDate segundoVto, Double tasaDeInteres) {
        super(descripcion, monto, moneda, vto, servicios, segundoVto);
        this.tasaDeInteres = tasaDeInteres;
    }

    @Override
    protected Double getTotal() {

        LocalDate hoy = LocalDate.now();

        if (hoy.isAfter(this.vto)) {

            long periodo = ChronoUnit.DAYS.between(this.vto, LocalDate.now());
            Double interes = super.getMonto() * ((this.tasaDeInteres * periodo / 365));

            return super.getMonto() + interes;
        }

        return super.getMonto();
    }
}