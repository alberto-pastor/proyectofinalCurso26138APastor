package com.beris.tejidos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.beris.tejidos.model.Categoria;
import com.beris.tejidos.model.Producto;
import com.beris.tejidos.service.CategoriaService;
import com.beris.tejidos.service.ProductoService;

@SpringBootApplication
public class TejidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TejidosApplication.class, args);
	}

	@Bean
	CommandLineRunner cargarDatos(ProductoService productoService, CategoriaService categoriaService){
		return args -> {
			if (productoService.listarTodos().isEmpty()) {
				Categoria indumentaria = categoriaService.guardar(new Categoria("Indumentaria","Prendas de vestir"));
				Categoria accesorios = categoriaService.guardar(new Categoria("Accesorios","Accesorios varios"));
				Categoria temporada = categoriaService.guardar(new Categoria("Temporada","Nuevos diseños"));
				productoService.guardar(new Producto("Chaleco a Crochet",55000,12,indumentaria));
				productoService.guardar(new Producto("Bufanda a Crochet",22000,10,accesorios));
				productoService.guardar(new Producto("Sweater Hombre Cuello V a Crochet",83000,5,indumentaria));
				productoService.guardar(new Producto("Gorro a Crochet",28000,5,accesorios));
				productoService.guardar(new Producto("Saco Granny Squares a Crochet", 95000, 4, indumentaria));
				productoService.guardar(new Producto("Guantes Mitones a Crochet", 18000, 15, accesorios));
				productoService.guardar(new Producto("Top Veraniego Calado a Crochet", 35000, 20, indumentaria));
				productoService.guardar(new Producto("Chal triangular a Crochet", 42000, 8, accesorios));
				productoService.guardar(new Producto("Cardigan Oversize a Crochet", 89000, 6, indumentaria));
				productoService.guardar(new Producto("Bolso Playero Toat Bag a Crochet", 32000, 12, accesorios));
				productoService.guardar(new Producto("Vestido de Playa a Crochet", 75000, 3, indumentaria));
				productoService.guardar(new Producto("Bandolera Mini a Crochet", 24000, 10, accesorios));
				productoService.guardar(new Producto("Polera de Invierno Trenzada a Crochet", 87000, 5, indumentaria));
				productoService.guardar(new Producto("Bufanda y Gorro a Crochet", 36000, 14, accesorios));
				productoService.guardar(new Producto("Vestido de noche a Crochet", 96000, 3, temporada));
			}
		};
	}
}
