package com.msvcproducto.producto.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.msvcproducto.producto.model.Producto;
import java.util.Optional;

public interface ProductoRepository extends MongoRepository<Producto, String> {
    Optional<Producto> findByCodProducto(String codProducto);
} 