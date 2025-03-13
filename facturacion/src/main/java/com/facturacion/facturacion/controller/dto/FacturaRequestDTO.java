package com.facturacion.facturacion.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "DTO para las solicitudes de factura")
public class FacturaRequestDTO {
    
    @Schema(description = "Código único de la factura", example = "1001")
    @NotNull(message = "El código de factura es obligatorio")
    private Integer codFactura;
    
    @Schema(description = "Tipo de identificación del cliente", example = "CED")
    @NotNull(message = "El tipo de identificación es obligatorio")
    @Size(min = 1, max = 3, message = "El tipo de identificación debe tener entre 1 y 3 caracteres")
    private String tipoIdentificacion;
    
    @Schema(description = "Número de identificación del cliente", example = "1234567890")
    @NotNull(message = "La identificación es obligatoria")
    @Size(min = 1, max = 20, message = "La identificación debe tener entre 1 y 20 caracteres")
    private String identificacion;
    
    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;
    
    @Schema(description = "Fecha de emisión de la factura", example = "2024-03-13T10:30:00")
    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;
    
    @Schema(description = "Subtotal de la factura", example = "100.00")
    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El subtotal no puede exceder 9999999.99")
    private BigDecimal subtotal;
    
    @Schema(description = "IVA de la factura", example = "12.00")
    @NotNull(message = "El IVA es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El IVA no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El IVA no puede exceder 9999999.99")
    private BigDecimal iva;
    
    @Schema(description = "Total de la factura", example = "112.00")
    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El total no puede exceder 9999999.99")
    private BigDecimal total;
    
    @Schema(description = "Lista de detalles de la factura")
    @NotNull(message = "La lista de detalles es obligatoria")
    private List<FacturaDetalleRequestDTO> detalles;
} 