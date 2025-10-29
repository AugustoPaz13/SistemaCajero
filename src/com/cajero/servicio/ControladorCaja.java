package com.cajero.servicio;

import com.cajero.modelo.*;
import com.cajero.simulacion.EntidadFinanciera;
import com.cajero.simulacion.Impresora;

public class ControladorCaja {

    private Caja cajaActual;

    public void iniciarSesion(Caja caja) {
        this.cajaActual = caja;
        System.out.println("Sesión iniciada para el cajero: " + caja.getIdCajero());
    }

    public void pagarConEfectivo(Transaccion transaccion, double montoRecibido) {
        System.out.println("-> Procesando pago en efectivo...");
        double saldo = transaccion.getSaldoPendiente();

        if (montoRecibido < saldo) {
            System.out.println("Error: El monto recibido es insuficiente.");
            return; // Flujo alternativo (Monto insuficiente)
        }

        // Se crea el pago (monto a pagar, monto recibido)
        Efectivo pagoEfectivo = new Efectivo(saldo, montoRecibido);
        transaccion.agregarPago(pagoEfectivo);

        System.out.printf("Pago registrado. Vuelto: $%.2f\n", pagoEfectivo.getVuelto());

        // Si el saldo queda en 0, se finaliza
        if (transaccion.getSaldoPendiente() <= 0) {
            finalizarTransaccion(transaccion);
        }
    }

    public void pagarConTarjeta(Transaccion transaccion, String numeroTarjeta) {
        System.out.println("-> Procesando pago con tarjeta...");
        double saldo = transaccion.getSaldoPendiente();

        // --- (Include: CA1a. Validar forma de pago) ---
        String estadoAuth = EntidadFinanciera.autorizarPago(numeroTarjeta, saldo);
        // ---------------------------------------------

        Tarjeta pagoTarjeta = new Tarjeta(saldo, numeroTarjeta, estadoAuth);

        if (pagoTarjeta.fueAprobado()) {
            transaccion.agregarPago(pagoTarjeta);
            System.out.println("Pago con tarjeta aprobado y registrado.");

            if (transaccion.getSaldoPendiente() <= 0) {
                finalizarTransaccion(transaccion);
            }
        } else {
            System.out.println("Error: El pago fue rechazado por la entidad financiera.");
        }
    }

    public void anularPago(Transaccion transaccion, String motivo) {
        // Precondición: Solo se anulan transacciones pendientes
        if (transaccion.getEstado() != EstadoTransaccion.PENDIENTE) {
            System.out.println("Error: No se puede anular una transacción ya pagada.");
            return;
        }

        // (Simula la confirmación del cajero)
        System.out.println("! ANULANDO TRANSACCIÓN: " + transaccion.getIdTransaccion());
        transaccion.setEstado(EstadoTransaccion.ANULADA);

        // Postcondición: "Toda anulación debe quedar registrada en bitácora"
        System.out.println("Bitácora: Anulación registrada. Motivo: " + motivo);
        // Aquí también debería revertirse el stock sumándolo de nuevo
    }


    private void finalizarTransaccion(Transaccion transaccion) {
        System.out.println("Finalizando transacción: " + transaccion.getIdTransaccion());

        transaccion.setEstado(EstadoTransaccion.PAGADA);

        Comprobante comprobante = new Comprobante(transaccion);
        transaccion.setComprobante(comprobante);

        String textoComprobante = comprobante.generarTexto();
        Impresora.imprimir(textoComprobante);

        System.out.println("Transacción completada y comprobante emitido.");
    }
}