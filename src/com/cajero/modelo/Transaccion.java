package com.cajero.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Transaccion {
    private String idTransaccion;
    private LocalDateTime fechaHora;
    private EstadoTransaccion estado;

    private List<DetalleTransaccion> detalles;
    private List<Pago> pagos;

    private Comprobante comprobante;
    private Caja caja;

    public Transaccion(String idTransaccion, Caja caja) {
        this.idTransaccion = idTransaccion;
        this.caja = caja;
        this.fechaHora = LocalDateTime.now();
        this.estado = EstadoTransaccion.PENDIENTE;
        this.detalles = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    public void agregarDetalle(Producto p, int cantidad) {
        // Aquí iría la lógica de descontar stock (CA1 Postcondición)
        p.setStock(p.getStock() - cantidad);
        this.detalles.add(new DetalleTransaccion(p, cantidad));
    }

    public void agregarPago(Pago pago) {
        this.pagos.add(pago);
        // Aquí se actualiza el registro de caja (CA1 Postcondición)
        this.caja.registrarIngreso(pago.getMonto());
    }

    public double getTotal() {
        double total = 0;
        for (DetalleTransaccion detalle : detalles) {
            total += detalle.getSubtotal();
        }
        return total;
    }

    public double getMontoPagado() {
        double pagado = 0;
        for (Pago pago : pagos) {
            pagado += pago.getMonto();
        }
        return pagado;
    }

    public double getSaldoPendiente() {
        return getTotal() - getMontoPagado();
    }

    // --- Getters y Setters ---
    public String getIdTransaccion() { return idTransaccion; }
    public EstadoTransaccion getEstado() { return estado; }
    public void setEstado(EstadoTransaccion estado) { this.estado = estado; }
    public List<DetalleTransaccion> getDetalles() { return detalles; }
    public void setComprobante(Comprobante comprobante) { this.comprobante = comprobante; }
}
