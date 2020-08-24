package ej5;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class ServicioDobleVencimiento extends Servicio {
    LocalDate segundoVto;

    public ServicioDobleVencimiento(String descripcion, Double monto, Moneda moneda, LocalDate vto, LocalDate segundoVto) {
        super(descripcion, monto, moneda, vto);
        this.segundoVto = segundoVto;
    }

    public ServicioDobleVencimiento(String descripcion, Double monto, Moneda moneda, LocalDate vto, ArrayList<Servicio> servicios, LocalDate segundoVto ) {
        super(descripcion, monto, moneda, vto, servicios);
        this.segundoVto = segundoVto;
    }

    @Override
    public void pagar()  {

        LocalDate hoy = LocalDate.now();

        if (hoy.isAfter(this.segundoVto)){
            this.estado = Estado.VENCIDO;
        }

        super.evaluarPago();
    }

    @Override
    protected ArrayList<LocalDate> getVtos() {
        ArrayList<LocalDate> listaVtos = new ArrayList<LocalDate>();

        listaVtos.add(this.vto);
        listaVtos.add(this.segundoVto);

        return listaVtos;
    }

}
