package com.cajero.modelo;

public class Producto {
    private String idProducto;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String idProducto, String nombre, double precio, int stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    //Getters y Setters
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}