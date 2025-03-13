package com.msvcproducto.producto.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Schema(description = "DTO para las solicitudes de producto")
public class ProductoRequestDTO {
    
    @Schema(description = "Código único del producto", example = "PROD001")
    @Size(max = 32, message = "El código del producto no puede exceder los 32 caracteres")
    private String codProducto;
    
    @Schema(description = "Nombre del producto", example = "Laptop HP")
    @Size(max = 64, message = "El nombre no puede exceder los 64 caracteres")
    private String nombre;
    
    @Schema(description = "Cantidad disponible en inventario", example = "100")
    @Min(value = 0, message = "La existencia no puede ser menor a 0")
    @Max(value = 99999, message = "La existencia no puede exceder 99999")
    private Integer existencia;
    
    @Schema(description = "Precio del producto", example = "999.99")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El precio no puede exceder 9999999.99")
    private BigDecimal precio;
} 