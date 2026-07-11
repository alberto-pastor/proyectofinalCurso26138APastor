package com.beris.tejidos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beris.tejidos.exception.ProductoNoEncontradoException;
import com.beris.tejidos.model.Producto;
import com.beris.tejidos.service.ProductoService;

import jakarta.validation.Valid;

//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = {"http://localhost:5500", "http://127.0.0.1:5500"})
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service){
        this.service = service;
    }

    @GetMapping
    public List<Producto> listarTodos(){
        return service.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int id) {
        try {
            Producto producto = service.obtenerPorId(id);
            return ResponseEntity.ok(producto);
        } catch (ProductoNoEncontradoException e){
            return ResponseEntity.notFound().build();
        }
        
    }
    
    @PostMapping("")
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto nuevoProducto) {
        Producto creado = service.guardar(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
        
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable int id, @Valid @RequestBody Producto datos) {
        try {
            Producto actualizado = service.actualizar(id, datos);
            return ResponseEntity.ok(actualizado);
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id){
        try {
            service.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (ProductoNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Producto>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Producto>> buscarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.buscarPorCategoria(categoria));
    }
}

