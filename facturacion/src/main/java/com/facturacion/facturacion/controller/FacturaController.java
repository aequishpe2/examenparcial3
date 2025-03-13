package com.facturacion.facturacion.controller;

import com.facturacion.facturacion.controller.dto.FacturaRequestDTO;
import com.facturacion.facturacion.controller.dto.FacturaResponseDTO;
import com.facturacion.facturacion.mapper.FacturaMapper;
import com.facturacion.facturacion.model.Factura;
import com.facturacion.facturacion.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/facturas")
@Tag(name = "Factura", description = "API para la gestión de facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private FacturaMapper facturaMapper;

    @Operation(summary = "Obtener todas las facturas")
    @ApiResponse(responseCode = "200", description = "Lista de facturas encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FacturaResponseDTO.class)))
    @GetMapping
    public ResponseEntity<List<FacturaResponseDTO>> listarFacturas() {
        List<Factura> facturas = facturaService.findAll();
        List<FacturaResponseDTO> facturasDTO = facturas.stream()
                .map(facturaMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(facturasDTO);
    }

    @Operation(summary = "Obtener una factura por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Factura encontrada",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = FacturaResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> obtenerFactura(@PathVariable String id) {
        Optional<Factura> factura = facturaService.findById(id);
        return factura.map(f -> ResponseEntity.ok(facturaMapper.toDTO(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener una factura por código")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Factura encontrada",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = FacturaResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<FacturaResponseDTO> obtenerFacturaPorCodigo(@PathVariable Integer codigo) {
        Optional<Factura> factura = facturaService.findByCodigo(codigo);
        return factura.map(f -> ResponseEntity.ok(facturaMapper.toDTO(f)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva factura")
    @ApiResponse(responseCode = "201", description = "Factura creada exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = FacturaResponseDTO.class)))
    @PostMapping
    public ResponseEntity<FacturaResponseDTO> crearFactura(@Valid @RequestBody FacturaRequestDTO facturaDTO) {
        Factura factura = facturaMapper.toEntity(facturaDTO);
        Factura nuevaFactura = facturaService.save(factura);
        return new ResponseEntity<>(facturaMapper.toDTO(nuevaFactura), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una factura existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Factura actualizada exitosamente",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = FacturaResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FacturaResponseDTO> actualizarFactura(@PathVariable String id,
                                                              @Valid @RequestBody FacturaRequestDTO facturaDTO) {
        Optional<Factura> facturaExistente = facturaService.findById(id);
        if (facturaExistente.isPresent()) {
            Factura factura = facturaMapper.toEntity(facturaDTO);
            factura.setId(id);
            Factura facturaActualizada = facturaService.save(factura);
            return ResponseEntity.ok(facturaMapper.toDTO(facturaActualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar una factura")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Factura eliminada exitosamente"),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable String id) {
        Optional<Factura> factura = facturaService.findById(id);
        if (factura.isPresent()) {
            facturaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
} 