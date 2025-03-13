package com.msvcproducto.producto.controller.mapper;

import com.msvcproducto.producto.controller.dto.ProductoRequestDTO;
import com.msvcproducto.producto.controller.dto.ProductoResponseDTO;
import com.msvcproducto.producto.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setExistencia(dto.getExistencia());
        producto.setPrecio(dto.getPrecio());
        return producto;
    }

    public ProductoResponseDTO toDTO(Producto entity) {
        ProductoResponseDTO dto = new ProductoResponseDTO();
        dto.setId(entity.getCod_Producto());
        dto.setCodigo(entity.getCodigo());
        dto.setNombre(entity.getNombre());
        dto.setExistencia(entity.getExistencia());
        dto.setPrecio(entity.getPrecio());
        return dto;
    }

    public void updateEntityFromDTO(ProductoRequestDTO dto, Producto entity) {
        entity.setCodigo(dto.getCodigo());
        entity.setNombre(dto.getNombre());
        entity.setExistencia(dto.getExistencia());
        entity.setPrecio(dto.getPrecio());
    }
} 