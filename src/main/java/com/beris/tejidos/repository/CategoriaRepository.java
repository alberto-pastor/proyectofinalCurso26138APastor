package com.beris.tejidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.beris.tejidos.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {
    
} 