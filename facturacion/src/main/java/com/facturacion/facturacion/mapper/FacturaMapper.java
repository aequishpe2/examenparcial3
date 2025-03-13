package com.facturacion.facturacion.mapper;

import com.facturacion.facturacion.controller.dto.FacturaRequestDTO;
import com.facturacion.facturacion.controller.dto.FacturaResponseDTO;
import com.facturacion.facturacion.model.Factura;
import org.springframework.stereotype.Component;

@Component
public class FacturaMapper {

    public FacturaResponseDTO toDTO(Factura factura) {
        FacturaResponseDTO dto = new FacturaResponseDTO();
        dto.setId(factura.getId());
        dto.setCodFactura(factura.getCodFactura());
        dto.setTipoIdentificacion(factura.getTipoIdentificacion());
        dto.setIdentificacion(factura.getIdentificacion());
        dto.setNombre(factura.getNombre());
        dto.setFecha(factura.getFecha());
        dto.setSubtotal(factura.getSubtotal());
        dto.setIva(factura.getIva());
        dto.setTotal(factura.getTotal());
        return dto;
    }

    public Factura toEntity(FacturaRequestDTO dto) {
        Factura factura = new Factura();
        factura.setCodFactura(dto.getCodFactura());
        factura.setTipoIdentificacion(dto.getTipoIdentificacion());
        factura.setIdentificacion(dto.getIdentificacion());
        factura.setNombre(dto.getNombre());
        factura.setFecha(dto.getFecha());
        factura.setSubtotal(dto.getSubtotal());
        factura.setIva(dto.getIva());
        factura.setTotal(dto.getTotal());
        return factura;
    }
} 