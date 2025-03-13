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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        factura.setSubtotal(BigDecimal.valueOf(subtotalFactura));
        factura.setIva(BigDecimal.valueOf(ivaFactura));
        factura.setTotal(BigDecimal.valueOf(totalFactura));

        logger.info("Factura calculada - Subtotal: {}, IVA: {}, Total: {}", 
                subtotalFactura, ivaFactura, totalFactura);

        return facturaRepository.save(factura);
    }

    @Override
    public List<Factura> findAll() {
        logger.info("Buscando todas las facturas");
        try {
            List<Factura> facturas = facturaRepository.findAll();
            logger.info("Se encontraron {} facturas", facturas.size());
            return facturas;
        } catch (Exception e) {
            logger.error("Error al buscar todas las facturas: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Factura> findById(String id) {
        logger.info("Buscando factura por ID: {}", id);
        try {
            Optional<Factura> factura = facturaRepository.findById(id);
            if (factura.isPresent()) {
                logger.info("Factura encontrada con ID: {}", id);
            } else {
                logger.warn("No se encontró factura con ID: {}", id);
            }
            return factura;
        } catch (Exception e) {
            logger.error("Error al buscar factura por ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Factura> findByCodigo(Integer codigo) {
        logger.info("Buscando factura por código: {}", codigo);
        try {
            Optional<Factura> factura = facturaRepository.findByCodFactura(codigo);
            if (factura.isPresent()) {
                logger.info("Factura encontrada con código: {}", codigo);
            } else {
                logger.warn("No se encontró factura con código: {}", codigo);
            }
            return factura;
        } catch (Exception e) {
            logger.error("Error al buscar factura por código {}: {}", codigo, e.getMessage());
            throw e;
        }
    }

    @Override
    public Factura save(Factura factura) {
        try {
            if (factura.getId() == null) {
                logger.info("Creando nueva factura con código: {}", factura.getCodFactura());
            } else {
                logger.info("Actualizando factura con ID: {}", factura.getId());
            }
            Factura savedFactura = facturaRepository.save(factura);
            logger.info("Factura guardada exitosamente con ID: {}", savedFactura.getId());
            return savedFactura;
        } catch (Exception e) {
            logger.error("Error al guardar factura: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteById(String id) {
        logger.info("Eliminando factura con ID: {}", id);
        try {
            facturaRepository.deleteById(id);
            logger.info("Factura eliminada exitosamente con ID: {}", id);
        } catch (Exception e) {
            logger.error("Error al eliminar factura con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
} 