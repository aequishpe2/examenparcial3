package com.msvcproducto.producto.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Document(collection = "productos")
public class Producto {
    @Id
    private String id;

    @Size(max = 32, message = "El código del producto no puede exceder los 32 caracteres")
    private String codProducto;

    @Size(max = 64, message = "El nombre no puede exceder los 64 caracteres")
    private String nombre;

    @Min(value = 0, message = "La existencia no puede ser menor a 0")
    @Max(value = 99999, message = "La existencia no puede exceder 99999")
    private Integer existencia;

    @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El precio no puede exceder 9999999.99")
    private BigDecimal precio;
}
