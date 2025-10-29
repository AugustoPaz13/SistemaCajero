package com.cajero.simulacion;

public class EntidadFinanciera {

    // Simula la validación de la tarjeta
    public static String autorizarPago(String numeroTarjeta, double monto) {
        System.out.println("[EntidadFinanciera] Conectando para autorizar $" + monto);

        // Simulación de rechazo
        if (numeroTarjeta.endsWith("9999")) {
            System.out.println("[EntidadFinanciera] RECHAZADO: Fondos insuficientes.");
            return "RECHAZADO";
        }

        // Simulación de aprobación
        System.out.println("[EntidadFinanciera] APROBADO.");
        return "APROBADO";
    }
}