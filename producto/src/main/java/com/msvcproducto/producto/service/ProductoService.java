package com.msvcproducto.producto.service;

import com.msvcproducto.producto.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(String id);
    Optional<Producto> findByCodigo(String codigo);
    Producto save(Producto producto);
    void deleteById(String id);
} 