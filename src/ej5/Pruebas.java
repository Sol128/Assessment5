package ej5;

import java.time.LocalDate;
import java.util.ArrayList;

public class Pruebas {

    public static void main(String[] args) {
        ServicioVencimientoUnico unicoVto = new ServicioVencimientoUnico("1", new Double(5000), Moneda.EUR, LocalDate.parse("2020-12-12"));

        ArrayList<Servicio> lista = new ArrayList<Servicio>();
        lista.add(unicoVto);

        ServicioInteresFijo interesFijo = new ServicioInteresFijo("2", new Double(7000), Moneda.ARS, LocalDate.parse("2020-12-31"),lista ,LocalDate.parse("2021-12-31"));
        ServicioInteresVariable interesVariable = new ServicioInteresVariable("2", new Double(1000), Moneda.ARS,
                LocalDate.parse("2020-12-31"), lista ,LocalDate.parse("2021-12-31"), new Double(0.5));

        interesVariable.agregarDependencia(interesFijo);

        unicoVto.pagar();
        unicoVto.getTotal();
        System.out.println(unicoVto.getMontoPagado());
        System.out.println(unicoVto.getTotal());

        interesFijo.pagar();
        System.out.println(interesFijo.getMontoPagado());
        System.out.println(interesFijo.getTotal());

        interesVariable.pagar();
        System.out.println(interesVariable.getMontoPagado());
        System.out.println(interesVariable.getTotal());






        ServicioVencimientoUnico unicoVtoVcdo = new ServicioVencimientoUnico("1", new Double(5000), Moneda.EUR, LocalDate.parse("2019-12-12"));
        ServicioInteresFijo interesFijoVcdo = new ServicioInteresFijo("2", new Double(7000), Moneda.ARS, LocalDate.parse("2019-12-31"),
                LocalDate.parse("2020-01-31"));
        ServicioInteresVariable interesVariableVcdo = new ServicioInteresVariable("2", new Double(1000), Moneda.ARS,
                LocalDate.parse("2019-12-31"), LocalDate.parse("2020-01-31"), new Double(0.5));

        unicoVtoVcdo.pagar();
        System.out.println(unicoVtoVcdo.getMontoPagado());
        System.out.println(unicoVtoVcdo.getTotal());

        interesFijoVcdo.pagar();
        System.out.println(interesFijoVcdo.getMontoPagado());
        System.out.println(interesFijoVcdo.getTotal());


        interesVariableVcdo.pagar();
        System.out.println(interesVariableVcdo.getMontoPagado());
        System.out.println(interesVariableVcdo.getTotal());


        ServicioInteresFijo interesFijocInteres = new ServicioInteresFijo("2", new Double(7000), Moneda.ARS,
                LocalDate.parse("2019-12-31"), LocalDate.parse("2025-12-31"));
        ServicioInteresVariable interesVariableVcdoCinteres = new ServicioInteresVariable("2", new Double(1000), Moneda.ARS,
                LocalDate.parse("2019-12-31"), LocalDate.parse("2025-12-31"), new Double(0.5));


        interesFijocInteres.pagar();
        System.out.println(interesFijocInteres.getMontoPagado());
        System.out.println(interesFijocInteres.getTotal());

        interesVariableVcdoCinteres.pagar();
        System.out.println(interesVariableVcdoCinteres.getMontoPagado());
        System.out.println(interesVariableVcdoCinteres.getTotal());
    }

}
