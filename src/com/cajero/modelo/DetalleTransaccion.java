package com.cajero.modelo;

public class DetalleTransaccion {
    private Producto producto;
    private int cantidad;
    private double precioUnitario; // Precio al momento de la venta

    public DetalleTransaccion(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public double getSubtotal() {
        return this.cantidad * this.precioUnitario;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
}