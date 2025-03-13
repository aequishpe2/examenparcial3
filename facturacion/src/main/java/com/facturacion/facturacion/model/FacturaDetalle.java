package com.facturacion.facturacion.model;

import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
public class FacturaDetalle {
    @Id
    private String codDetalle;
    private String codProducto;
    private String codFactura;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private double iva;
    private double total;
    
    private String nombreProducto;
} 