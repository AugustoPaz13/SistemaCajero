package com.cajero.modelo;

public class Tarjeta extends Pago {

    private String numeroTarjeta;
    private String estadoAutorizacion; //"APROBADO", "RECHAZADO"

    public Tarjeta(double monto, String numeroTarjeta, String estadoAutorizacion) {
        super(monto);
        this.numeroTarjeta = numeroTarjeta;
        this.estadoAutorizacion = estadoAutorizacion;
    }

    public boolean fueAprobado() {
        return "APROBADO".equals(estadoAutorizacion);
    }
}
