package com.cajero.modelo;

import java.time.LocalDateTime;

public class Comprobante {
    private String idComprobante;
    private LocalDateTime fechaHora;
    private Transaccion transaccion;

    public Comprobante(Transaccion transaccion) {
        this.idComprobante = "C-" + transaccion.getIdTransaccion();
        this.fechaHora = LocalDateTime.now();
        this.transaccion = transaccion;
    }

    //Simula la generación del texto para la impresora.
    // Implementa el flujo de CA1b.

    public String generarTexto() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- COMPROBANTE DE PAGO ---\n");
        sb.append("ID Transacción: ").append(transaccion.getIdTransaccion()).append("\n");
        sb.append("Fecha: ").append(fechaHora).append("\n");
        sb.append("----------------------------\n");
        sb.append("Cant.  Producto      Subtotal\n");

        for (DetalleTransaccion d : transaccion.getDetalles()) {
            sb.append(String.format("%-6d %-13s $%.2f\n",
                    d.getCantidad(),
                    d.getProducto().getNombre(),
                    d.getSubtotal()));
        }

        sb.append("----------------------------\n");
        sb.append(String.format("TOTAL PAGADO: $%.2f\n", transaccion.getMontoPagado()));
        sb.append("--- Gracias por su compra ---");
        return sb.toString();
    }
}