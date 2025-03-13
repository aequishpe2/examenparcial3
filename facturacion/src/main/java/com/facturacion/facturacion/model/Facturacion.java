package com.facturacion.facturacion.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "facturacion")
public class Facturacion {

    @Id
    private int cod_Factura;
    private String tipo_Identificacion;
    private String identificacion;
    private String nombre;
    private LocalDate fecha;
    private String subtotal;
    private String iva;
    private String total;
    
}
