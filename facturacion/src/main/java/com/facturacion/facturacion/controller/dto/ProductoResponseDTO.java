package com.facturacion.facturacion.controller.dto;

import lombok.Data;

@Data
public class ProductoResponseDTO {
    private String codProducto;
    private String nombre;
    private double precio;
} 