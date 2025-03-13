package com.facturacion.facturacion.service;

import com.facturacion.facturacion.model.Factura;
import com.facturacion.facturacion.controller.dto.FacturaRequestDTO;
import java.util.List;
import java.util.Optional;

public interface FacturaService {
    List<Factura> findAll();
    Optional<Factura> findById(String id);
    Optional<Factura> findByCodigo(Integer codigo);
    Factura save(Factura factura);
    void deleteById(String id);
    Factura crearFactura(FacturaRequestDTO facturaDTO);
} 