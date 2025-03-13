package com.msvcproducto.producto.controller;

import com.msvcproducto.producto.controller.dto.ProductoRequestDTO;
import com.msvcproducto.producto.controller.dto.ProductoResponseDTO;
import com.msvcproducto.producto.controller.mapper.ProductoMapper;
import com.msvcproducto.producto.model.Producto;
import com.msvcproducto.producto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Producto", description = "API para la gestión de productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoMapper productoMapper;

    @Operation(summary = "Obtener todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos encontrada",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductoResponseDTO.class)))
    @GetMapping
    public List<ProductoResponseDTO> listarProductos() {
        return productoService.findAll().stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Obtener un producto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProductoResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorId(
            @Parameter(description = "ID del producto") @PathVariable String id) {
        return productoService.findById(id)
                .map(producto -> ResponseEntity.ok(productoMapper.toDTO(producto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Obtener un producto por código")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProductoResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorCodigo(
            @Parameter(description = "Código del producto") @PathVariable String codigo) {
        return productoService.findByCodigo(codigo)
                .map(producto -> ResponseEntity.ok(productoMapper.toDTO(producto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponse(responseCode = "200", description = "Producto creado exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductoResponseDTO.class)))
    @PostMapping
    public ProductoResponseDTO crearProducto(
            @Parameter(description = "Datos del producto", 
                    schema = @Schema(implementation = ProductoRequestDTO.class))
            @RequestBody ProductoRequestDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        return productoMapper.toDTO(productoService.save(producto));
    }

    @Operation(summary = "Actualizar un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProductoResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @Parameter(description = "ID del producto") @PathVariable String id,
            @Parameter(description = "Datos actualizados del producto",
                    schema = @Schema(implementation = ProductoRequestDTO.class))
            @RequestBody ProductoRequestDTO productoDTO) {
        return productoService.findById(id)
                .map(productoExistente -> {
                    productoMapper.updateEntityFromDTO(productoDTO, productoExistente);
                    productoExistente.setCod_Producto(id);
                    Producto productoActualizado = productoService.save(productoExistente);
                    return ResponseEntity.ok(productoMapper.toDTO(productoActualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @Parameter(description = "ID del producto") @PathVariable String id) {
        if (productoService.findById(id).isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
