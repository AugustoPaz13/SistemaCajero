package com.cajero.modelo;

import java.util.ArrayList;
import java.util.List;

public class Caja {
    private String idCajero;
    private double saldoEnCaja; // Es el 'registro de caja'
    private List<Transaccion> transaccionesRegistradas;

    public Caja(String idCajero, double saldoInicial) {
        this.idCajero = idCajero;
        this.saldoEnCaja = saldoInicial;
        this.transaccionesRegistradas = new ArrayList<>();
    }

    public void registrarTransaccion(Transaccion t) {
        this.transaccionesRegistradas.add(t);
    }

    // Postcondición de CA1: "Se actualiza el registro de caja"
    public void registrarIngreso(double monto) {
        this.saldoEnCaja += monto;
    }

    public double getSaldoEnCaja() { return saldoEnCaja; }

    public String getIdCajero() {
        return idCajero;
    }
}