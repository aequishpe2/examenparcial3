package com.facturacion.facturacion.service;

import com.facturacion.facturacion.controller.dto.FacturaRequestDTO;
import com.facturacion.facturacion.model.Factura;

public interface FacturaService {
    Factura crearFactura(FacturaRequestDTO facturaDTO);
} 