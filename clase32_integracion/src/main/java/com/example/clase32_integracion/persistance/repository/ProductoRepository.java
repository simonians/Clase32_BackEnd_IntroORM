package com.example.clase32_integracion.persistance.repository;

import com.example.clase32_integracion.persistance.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

}
