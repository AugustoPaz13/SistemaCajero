package com.cajero.modelo;

public class Efectivo extends Pago {
    private double montoRecibido;

    public Efectivo(double montoACubrir, double montoRecibido) {
        super(montoACubrir);
        this.montoRecibido = montoRecibido;
    }

    public double getVuelto() {
        // El vuelto se calcula sobre lo recibido vs lo que se pagó
        return montoRecibido - super.monto;
    }
}