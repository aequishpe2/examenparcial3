package com.msvcproducto.producto.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para las solicitudes de producto")
public class ProductoRequestDTO {
    
    @Schema(description = "Código único del producto", example = "PROD001")
    private String codProducto;
    
    @Schema(description = "Nombre del producto", example = "Laptop HP")
    private String nombre;
    
    @Schema(description = "Cantidad disponible en inventario", example = "100")
    private int existencia;
    
    @Schema(description = "Precio del producto", example = "999.99")
    private double precio;
} 