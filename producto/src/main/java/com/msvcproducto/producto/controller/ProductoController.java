package com.msvcproducto.producto.controller;

import com.msvcproducto.producto.controller.dto.ProductoRequestDTO;
import com.msvcproducto.producto.controller.dto.ProductoResponseDTO;
import com.msvcproducto.producto.controller.mapper.ProductoMapper;
import com.msvcproducto.producto.model.Producto;
import com.msvcproducto.producto.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos() {
        List<Producto> productos = productoService.findAll();
        List<ProductoResponseDTO> productosDTO = productos.stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(productosDTO);
    }

    @Operation(summary = "Obtener un producto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProductoResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerProducto(@PathVariable String id) {
        Optional<Producto> producto = productoService.findById(id);
        return producto.map(p -> ResponseEntity.ok(productoMapper.toDTO(p)))
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
    public ResponseEntity<ProductoResponseDTO> obtenerProductoPorCodigo(@PathVariable String codigo) {
        Optional<Producto> producto = productoService.findByCodigo(codigo);
        return producto.map(p -> ResponseEntity.ok(productoMapper.toDTO(p)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear un nuevo producto")
    @ApiResponse(responseCode = "200", description = "Producto creado exitosamente",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductoResponseDTO.class)))
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto nuevoProducto = productoService.save(producto);
        return new ResponseEntity<>(productoMapper.toDTO(nuevoProducto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ProductoResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable String id, 
                                                                @Valid @RequestBody ProductoRequestDTO productoDTO) {
        Optional<Producto> productoExistente = productoService.findById(id);
        if (productoExistente.isPresent()) {
            Producto producto = productoMapper.toEntity(productoDTO);
            producto.setId(id);
            Producto productoActualizado = productoService.save(producto);
            return ResponseEntity.ok(productoMapper.toDTO(productoActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Eliminar un producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String id) {
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/existencia")
    public ResponseEntity<Void> actualizarExistencia(@PathVariable String id, 
                                                    @Valid @RequestBody Integer cantidad) {
        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            productoService.updateExistencia(id, cantidad);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
