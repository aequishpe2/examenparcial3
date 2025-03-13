package com.facturacion.facturacion.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "DTO para las respuestas de factura")
public class FacturaResponseDTO {
    
    @Schema(description = "ID único de la factura", example = "507f1f77bcf86cd799439011")
    private String id;
    
    @Schema(description = "Código único de la factura", example = "1001")
    private Integer codFactura;
    
    @Schema(description = "Tipo de identificación del cliente", example = "CED")
    private String tipoIdentificacion;
    
    @Schema(description = "Número de identificación del cliente", example = "1234567890")
    private String identificacion;
    
    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    private String nombre;
    
    @Schema(description = "Fecha de emisión de la factura", example = "2024-03-13T10:30:00")
    private LocalDateTime fecha;
    
    @Schema(description = "Subtotal de la factura", example = "100.00")
    private BigDecimal subtotal;
    
    @Schema(description = "IVA de la factura", example = "12.00")
    private BigDecimal iva;
    
    @Schema(description = "Total de la factura", example = "112.00")
    private BigDecimal total;
} 