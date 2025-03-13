package com.facturacion.facturacion.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
@Schema(description = "DTO para los detalles de la factura")
public class FacturaDetalleRequestDTO {
    
    @Schema(description = "Código del producto", example = "PROD001")
    @NotNull(message = "El código del producto es obligatorio")
    private String codProducto;
    
    @Schema(description = "Cantidad del producto", example = "2")
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;
} 