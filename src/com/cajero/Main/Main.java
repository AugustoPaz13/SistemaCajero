package com.cajero.Main;

import com.cajero.modelo.*;
import com.cajero.servicio.ControladorCaja;

public class Main {

    public static void main(String[] args) {

        // --- 1. SETUP INICIAL (Datos de prueba) ---
        System.out.println("=== INICIANDO SISTEMA ===");

        // Creamos productos en el inventario
        Producto pizza = new Producto("P01", "Pizza Muzz", 1500.0, 50);
        Producto coca = new Producto("B02", "Coca-Cola", 500.0, 100);

        // Creamos el controlador y la caja
        ControladorCaja controlador = new ControladorCaja();
        Caja caja01 = new Caja("Cajero-Augusto", 10000.0); // Saldo inicial

        // Precondición: Cajero inicia sesión
        controlador.iniciarSesion(caja01);
        System.out.println("Saldo inicial en caja: $" + caja01.getSaldoEnCaja());


        // --- 2. SIMULACIÓN: Transacción 1 (Pago exitoso con Tarjeta) ---
        System.out.println("\n=== Nueva Transacción T-001 ===");

        // Precondición: "Debe existir una venta... pendiente de cobro"
        Transaccion t1 = new Transaccion("T-001", caja01);
        t1.agregarDetalle(pizza, 1); // 1 Pizza
        t1.agregarDetalle(coca, 2);  // 2 Coca-Cola

        System.out.println("Total a pagar T-001: $" + t1.getTotal()); // Debería ser 2500

        // Disparador: "El cliente indica al cajero la intención de pagar"
        // Flujo: Cajero selecciona "Pagar con Tarjeta"
        controlador.pagarConTarjeta(t1, "4500-1234-5678-0000");

        System.out.println("Estado final T-001: " + t1.getEstado());
        System.out.println("Saldo actual en caja: $" + caja01.getSaldoEnCaja()); // Sube a 12500


        // --- 3. SIMULACIÓN: Transacción 2 (Pago Rechazado y luego Efectivo) ---
        System.out.println("\n=== Nueva Transacción T-002 ===");
        Transaccion t2 = new Transaccion("T-002", caja01);
        t2.agregarDetalle(pizza, 1); // 1 Pizza (1500)

        System.out.println("Total a pagar T-002: $" + t2.getTotal());

        // Flujo Alternativo (a): Pago Rechazado
        System.out.println("--- Intento 1: Tarjeta Rechazada ---");
        controlador.pagarConTarjeta(t2, "9999-9999-9999-9999"); // Tarjeta de prueba rechazada

        System.out.println("Estado T-002 (debe ser PENDIENTE): " + t2.getEstado());

        // Flujo: "El cajero informa al cliente y le solicita... otro método"
        System.out.println("--- Intento 2: Pago en Efectivo ---");
        // El cliente paga los 1500 con un billete de 2000
        controlador.pagarConEfectivo(t2, 2000.0);

        System.out.println("Estado final T-002: " + t2.getEstado());
        System.out.println("Saldo actual en caja: $" + caja01.getSaldoEnCaja()); // Sube a 14000
    }
}