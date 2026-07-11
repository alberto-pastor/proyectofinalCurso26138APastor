package com.beris.tejidos.service;

//import com.beris.tejidos.exception.PrecioInvalidoException;
import com.beris.tejidos.exception.ProductoNoEncontradoException;
//import com.beris.tejidos.exception.StockInsuficienteException;
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
