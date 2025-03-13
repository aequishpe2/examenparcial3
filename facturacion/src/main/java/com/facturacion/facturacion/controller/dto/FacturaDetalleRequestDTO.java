package com.facturacion.facturacion.controller.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "DTO para la solicitud de detalle de factura")
public class FacturaDetalleRequestDTO {
    @Schema(description = "Código del producto", example = "PROD001")
    private String codProducto;
    
    @Schema(description = "Cantidad del producto", example = "2")
    private int cantidad;
} 