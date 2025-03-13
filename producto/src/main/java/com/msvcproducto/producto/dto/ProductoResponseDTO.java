package com.msvcproducto.producto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para las respuestas de producto")
public class ProductoResponseDTO {
    
    @Schema(description = "ID del producto", example = "507f1f77bcf86cd799439011")
    private String id;
    
    @Schema(description = "Código único del producto", example = "PROD001")
    private String codigo;
    
    @Schema(description = "Nombre del producto", example = "Laptop HP")
    private String nombre;
    
    @Schema(description = "Cantidad disponible en inventario", example = "100")
    private int existencia;
    
    @Schema(description = "Precio del producto", example = "999.99")
    private double precio;
} 