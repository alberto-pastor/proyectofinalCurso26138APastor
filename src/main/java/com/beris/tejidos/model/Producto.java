package com.beris.tejidos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank(message = "El nombre del producto no puede estar vacio")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Positive(message = "El precio debe ser mayor a cero")
    @Column(name = "precio", nullable = false)
    private double precio;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Column(name = "stock", nullable = false)
    private int stock;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Producto(String nombre, double precio, int stock, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    // Constructor vacío: útil para crear un Producto y completarlo
    // con setters después. También lo necesitará Spring/JPA más adelante en el curso.
    public Producto() {
    }

    // Getters y setters: la única forma de acceder o modificar
    // los atributos privados desde afuera de la clase.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    // toString() sobreescribe el método heredado de Object.
    // Sirve para mostrar el producto de forma legible al listarlo
    // en consola. Cuando hagamos System.out.println(producto), Java
    // llama automáticamente a este método.
    @Override
    public String toString() {
        return "ID: " + id +
                " | " + nombre +
                " | $" + precio +
                " | Stock: " + stock +
                " | Categoría: " + categoria;
    }
}
