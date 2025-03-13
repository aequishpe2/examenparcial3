package com.facturacion.facturacion.repository;

import com.facturacion.facturacion.model.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface FacturaRepository extends MongoRepository<Factura, String> {
    Optional<Factura> findByCodFactura(Integer codFactura);
} 