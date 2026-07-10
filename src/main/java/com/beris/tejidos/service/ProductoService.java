package com.beris.tejidos.service;

import com.beris.tejidos.exception.PrecioInvalidoException;
import com.beris.tejidos.exception.ProductoNoEncontradoException;
import com.beris.tejidos.exception.StockInsuficienteException;
import com.beris.tejidos.model.Producto;
import com.beris.tejidos.repository.ProductoRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository){
        this.repository = repository;
    }

    public Producto guardar(Producto p) {
        if (p.getNombre() == null || p.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (p.getPrecio() <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se recibió: " + p.getPrecio());
        }
        if (p.getStock() < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo. Se recibió: " + p.getStock());
        }
        
        return repository.save(p);
    }

    public List<Producto> listarTodos() {
        return repository.findAll();
    }

    public Producto obtenerPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("No se encontró un producto con id " + id));
    }

    public Producto actualizar(int id, Producto datos) {
        if (datos.getNombre() == null || datos.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío.");
        }
        if (datos.getPrecio() <= 0) {
            throw new PrecioInvalidoException("El precio debe ser mayor a cero. Se recibió: " + datos.getPrecio());
        }
        if (datos.getStock() < 0) {
            throw new StockInsuficienteException("El stock no puede ser negativo. Se recibió: " + datos.getStock());
        }
        
        Producto p = obtenerPorId(id);

        p.setNombre(datos.getNombre());
        p.setPrecio(datos.getPrecio());
        p.setStock(datos.getStock());
        p.setCategoria(datos.getCategoria());

        return repository.save(p);
    }

    public void eliminar(int id) {
        Producto p = obtenerPorId(id);
        repository.delete(p);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return repository.findByNombreContaining(nombre);
    }

    public List<Producto> buscarPorCategoria(String categoria) {
        return repository.findByCategoriaNombreContainingIgnoreCase(categoria);
    }
}
