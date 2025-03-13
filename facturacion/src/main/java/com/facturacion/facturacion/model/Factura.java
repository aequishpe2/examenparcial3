package com.facturacion.facturacion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "facturas")
public class Factura {
    @Id
    private String codFactura;
    private String tipoIdentificacion;
    private String identificacion;
    private String nombre;
    private LocalDateTime fecha;
    private double subtotal;
    private double iva;
    private double total;
    private List<FacturaDetalle> detalles;
} 