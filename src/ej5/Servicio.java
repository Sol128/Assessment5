package ej5;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class Servicio {
    protected static Integer contador = new Integer(0);
    protected static Integer maxDependencias = 4;
    protected String id;
    protected String descripcion;
    protected Double monto;
    protected Moneda moneda;
    protected LocalDate vto;
    protected Estado estado;
    protected LocalDate fechaDePago;
    protected Double montoPagado = new Double(0);
    protected ArrayList<Servicio> dependencias = new ArrayList<Servicio>();

    public Servicio(String descripcion, Double monto, Moneda moneda, LocalDate vto) {
        contador++;
        if (contador.toString().length() > 20) {
            this.id = contador.toString().substring(0, 20);
        } else {
            this.id = contador.toString();
        }
        this.descripcion = descripcion;
        this.monto = monto;
        this.moneda = moneda;
        this.vto = vto;
        this.estado = Estado.ACTIVO;
    }

    public Servicio(String descripcion, Double monto, Moneda moneda, LocalDate vto, ArrayList<Servicio>  dependencias) {
        contador++;
        if (contador.toString().length() > 20) {
            this.id = contador.toString().substring(0, 20);
        } else {
            this.id = contador.toString();
        }
        this.descripcion = descripcion;
        this.monto = monto;
        this.moneda = moneda;
        this.vto = vto;
        this.estado = Estado.ACTIVO;

        for( Servicio servicio : dependencias){
            if (this.dependencias.size() <= maxDependencias) {
                this.dependencias.add(servicio);
            }
        }
    }


    public abstract void pagar();

    //Devuelve el monto del servicio + el interes en caso de que tenga
    protected abstract Double getTotal();

    protected String getId(){
        return this.id;
    }

    protected String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaDePago() {
        return fechaDePago;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    protected abstract ArrayList<LocalDate> getVtos();

    protected Estado getEstado() {
        return estado;
    }


    protected Double getMonto(){
        return this.monto;
    }

    protected void evaluarEstadoServicio()  throws NoSePuedePagarServicioVencidoException, ServicioYaSePagoException{

            switch (this.estado) {
                case ACTIVO:
                    this.estado = Estado.PAGADO;
                    this.fechaDePago = LocalDate.now();
                    this.montoPagado = this.getTotal();
                    break;
                case PAGADO:
                    throw new ServicioYaSePagoException();
                case VENCIDO:
                    throw new NoSePuedePagarServicioVencidoException();
            }
    }

    protected void evaluarDependencia() throws ServicioDelqueDependeImpagoException {

        if (!this.dependencias.isEmpty() || this.dependencias != null) {
            for( Servicio dependencia: this.dependencias) {
                if (dependencia.getEstado().equals(Estado.VENCIDO)
                        || dependencia.getEstado().equals(Estado.ACTIVO)) {
                    throw new ServicioDelqueDependeImpagoException();
                }
            }
        }

    }

    protected void evaluarPago(){
        this.evaluarDependencia();
        this.evaluarEstadoServicio();
    }


    protected void agregarDependencia(Servicio servicio){
        if(this.dependencias.size() <= maxDependencias){
            this.dependencias.add(servicio);
        } else {
            System.out.println("El servicio ya alcanzo el máximo de dependencias");
        }
    }


    protected LocalDate getProxVto() throws ElServicioEstaVencidoOPagadoException {

        LocalDate hoy = LocalDate.now();

        if (hoy.isAfter(this.vto)) {
            this.estado = Estado.VENCIDO;
        }


        if (this.estado.equals( Estado.ACTIVO)) {
            ArrayList<LocalDate> vencimientos = new ArrayList<LocalDate>();

            //agrego los vencimientos del servicio
            for (LocalDate vtoDelServicio : this.getVtos()) {
                vencimientos.add(vtoDelServicio);
            }

            //agrego los vencimientos de los servicios de los que depende

            for (Servicio dependencia : this.dependencias) {
                for (LocalDate vtoDependencia : dependencia.getVtos()) {
                    vencimientos.add(vtoDependencia);
                }
            }

            Collections.sort(vencimientos);

            for (LocalDate vencimiento : vencimientos) {
                if (hoy.isBefore(vencimiento)) {
                    return vencimiento;
                }
            }

            return hoy;

        } else {

            throw new ElServicioEstaVencidoOPagadoException();
        }
    }
}
