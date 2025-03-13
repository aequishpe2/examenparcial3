package com.facturacion.facturacion.controller;

import com.msvcfacturacion.facturacion.dto.FacturaRequestDTO;
import com.msvcfacturacion.facturacion.model.Factura;
import com.msvcfacturacion.facturacion.service.FacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturas")
@Tag(name = "Factura", description = "API para la gestión de facturas")
public class FacturaController {

    private static final Logger logger = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    private FacturaService facturaService;

    @Operation(summary = "Crear una nueva factura")
    @ApiResponse(responseCode = "200", description = "Factura creada exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Factura.class)))
    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody FacturaRequestDTO facturaDTO) {
        logger.info("Recibida solicitud para crear factura para: {}", facturaDTO.getNombre());
        try {
            Factura factura = facturaService.crearFactura(facturaDTO);
            logger.info("Factura creada exitosamente con ID: {}", factura.getId());
            return ResponseEntity.ok(factura);
        } catch (Exception e) {
            logger.error("Error al crear factura: {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
} 