package com.facturacion.facturacion.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.facturacion.facturacion.model.Factura;

public interface FacturaRepository extends MongoRepository<Factura, String> {
} 