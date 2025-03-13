package com.msvcproducto.producto.mapper;

import com.msvcproducto.producto.controller.dto.ProductoRequestDTO;
import com.msvcproducto.producto.controller.dto.ProductoResponseDTO;
import com.msvcproducto.producto.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoResponseDTO toDTO(Producto producto) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setCodProducto(producto.getCodProducto());
        dto.setNombre(producto.getNombre());
        dto.setExistencia(producto.getExistencia());
        dto.setPrecio(producto.getPrecio());
        return dto;
    }

    public Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setCodProducto(dto.getCodProducto());
        producto.setNombre(dto.getNombre());
        producto.setExistencia(dto.getExistencia());
        producto.setPrecio(dto.getPrecio());
        return producto;
    }
} 