package com.msvcproducto.producto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "productos")
public class Producto {
    @Id
    private String cod_Producto;
    private String codigo;
    private String nombre;
    private int existencia;
    private double precio;


}
