package com.msvcproducto.producto.controller.mapper;

import com.msvcproducto.producto.controller.dto.ProductoRequestDTO;
import com.msvcproducto.producto.controller.dto.ProductoResponseDTO;
import com.msvcproducto.producto.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setCodProducto(dto.getCodProducto());
        producto.setNombre(dto.getNombre());
        producto.setExistencia(dto.getExistencia());
        producto.setPrecio(dto.getPrecio());
        return producto;
    }

    public ProductoResponseDTO toDTO(Producto entity) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setCodProducto(entity.getCodProducto());
        dto.setNombre(entity.getNombre());
        dto.setExistencia(entity.getExistencia());
        dto.setPrecio(entity.getPrecio());
        return dto;
    }

    public void updateEntityFromDTO(ProductoRequestDTO dto, Producto entity) {
        entity.setCodProducto(dto.getCodProducto());
        entity.setNombre(dto.getNombre());
        entity.setExistencia(dto.getExistencia());
        entity.setPrecio(dto.getPrecio());
    }
} 