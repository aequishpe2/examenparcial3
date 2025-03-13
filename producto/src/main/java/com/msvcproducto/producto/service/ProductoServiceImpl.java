package com.msvcproducto.producto.service;

import com.msvcproducto.producto.model.Producto;
import com.msvcproducto.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(String id) {
        return productoRepository.findById(id);
    }

    @Override
    public Optional<Producto> findByCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(String id) {
        productoRepository.deleteById(id);
    }
} 