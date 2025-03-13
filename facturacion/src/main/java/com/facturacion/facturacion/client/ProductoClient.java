package com.facturacion.facturacion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.facturacion.facturacion.controller.dto.ProductoResponseDTO;

@FeignClient(name = "producto-service", url = "${producto.service.url}")
public interface ProductoClient {
    @GetMapping("/api/productos/codigo/{codigo}")
    ProductoResponseDTO obtenerProductoPorCodigo(@PathVariable("codigo") String codigo);
} 