package com.cajero.modelo;

public abstract class Pago {
    private String idPago;
    protected double monto; // Monto que este pago cubre de la transacción

    public Pago(double monto) {
        this.idPago = "P-" + System.currentTimeMillis();
        this.monto = monto;
    }

    public double getMonto() { return monto; }
}