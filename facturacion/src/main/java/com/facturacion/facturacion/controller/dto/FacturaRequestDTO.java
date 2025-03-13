package com.facturacion.facturacion.controller.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Data
@Schema(description = "DTO para la solicitud de creación de factura")
public class FacturaRequestDTO {
    @Schema(description = "Tipo de identificación del cliente", example = "CEDULA")
    private String tipoIdentificacion;
    
    @Schema(description = "Número de identificación del cliente", example = "1234567890")
    private String identificacion;
    
    @Schema(description = "Nombre del cliente", example = "Juan Pérez")
    private String nombre;
    
    @Schema(description = "Lista de detalles de la factura")
    private List<FacturaDetalleRequestDTO> detalles;
} 