package com.facturacion.facturacion.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "facturas")
public class Factura {
    @Id
    private String id;

    @NotNull(message = "El código de factura es obligatorio")
    private Integer codFactura;

    @NotNull(message = "El tipo de identificación es obligatorio")
    @Size(min = 1, max = 3, message = "El tipo de identificación debe tener entre 1 y 3 caracteres")
    private String tipoIdentificacion;

    @NotNull(message = "La identificación es obligatoria")
    @Size(min = 1, max = 20, message = "La identificación debe tener entre 1 y 20 caracteres")
    private String identificacion;

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres")
    private String nombre;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDateTime fecha;

    @NotNull(message = "El subtotal es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El subtotal no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El subtotal no puede exceder 9999999.99")
    private BigDecimal subtotal;

    @NotNull(message = "El IVA es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El IVA no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El IVA no puede exceder 9999999.99")
    private BigDecimal iva;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El total no puede ser menor a 0")
    @DecimalMax(value = "9999999.99", message = "El total no puede exceder 9999999.99")
    private BigDecimal total;

    private List<FacturaDetalle> detalles;
} 