package com.facturacion.facturacion.service;

import com.facturacion.facturacion.client.ProductoClient;
import com.facturacion.facturacion.controller.dto.FacturaRequestDTO;
import com.facturacion.facturacion.controller.dto.ProductoResponseDTO;
import com.facturacion.facturacion.model.Factura;
import com.facturacion.facturacion.model.FacturaDetalle;
import com.facturacion.facturacion.repository.FacturaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacturaServiceImpl implements FacturaService {

    private static final Logger logger = LoggerFactory.getLogger(FacturaServiceImpl.class);
    private static final double IVA_RATE = 0.12;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProductoClient productoClient;

    @Override
    public Factura crearFactura(FacturaRequestDTO facturaDTO) {
        logger.info("Iniciando creación de factura para cliente: {}", facturaDTO.getNombre());
        
        Factura factura = new Factura();
        factura.setTipoIdentificacion(facturaDTO.getTipoIdentificacion());
        factura.setIdentificacion(facturaDTO.getIdentificacion());
        factura.setNombre(facturaDTO.getNombre());
        factura.setFecha(LocalDateTime.now());

        List<FacturaDetalle> detalles = new ArrayList<>();
        double subtotalFactura = 0;
        double ivaFactura = 0;
        double totalFactura = 0;

        for (var detalleDTO : facturaDTO.getDetalles()) {
            try {
                ProductoResponseDTO producto = productoClient.obtenerProductoPorCodigo(detalleDTO.getCodProducto());
                logger.info("Producto encontrado: {} con precio: {}", producto.getNombre(), producto.getPrecio());

                FacturaDetalle detalle = new FacturaDetalle();
                detalle.setCodProducto(producto.getCodProducto());
                detalle.setNombreProducto(producto.getNombre());
                detalle.setCantidad(detalleDTO.getCantidad());
                detalle.setPrecioUnitario(producto.getPrecio());

                double subtotalDetalle = detalle.getPrecioUnitario() * detalle.getCantidad();
                double ivaDetalle = subtotalDetalle * IVA_RATE;
                double totalDetalle = subtotalDetalle + ivaDetalle;

                detalle.setSubtotal(subtotalDetalle);
                detalle.setIva(ivaDetalle);
                detalle.setTotal(totalDetalle);

                subtotalFactura += subtotalDetalle;
                ivaFactura += ivaDetalle;
                totalFactura += totalDetalle;

                detalles.add(detalle);
            } catch (Exception e) {
                logger.error("Error al procesar producto {}: {}", detalleDTO.getCodProducto(), e.getMessage());
                throw new RuntimeException("Error al procesar el producto: " + detalleDTO.getCodProducto());
            }
        }

        factura.setDetalles(detalles);
        factura.setSubtotal(subtotalFactura);
        factura.setIva(ivaFactura);
        factura.setTotal(totalFactura);

        logger.info("Factura calculada - Subtotal: {}, IVA: {}, Total: {}", 
                subtotalFactura, ivaFactura, totalFactura);

        return facturaRepository.save(factura);
    }
} 