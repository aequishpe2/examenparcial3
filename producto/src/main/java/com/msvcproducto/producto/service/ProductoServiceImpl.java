package com.msvcproducto.producto.service;

import com.msvcproducto.producto.model.Producto;
import com.msvcproducto.producto.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        logger.info("Buscando todos los productos");
        try {
            List<Producto> productos = productoRepository.findAll();
            logger.info("Se encontraron {} productos", productos.size());
            return productos;
        } catch (Exception e) {
            logger.error("Error al buscar todos los productos: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Producto> findById(String id) {
        logger.info("Buscando producto por ID: {}", id);
        try {
            Optional<Producto> producto = productoRepository.findById(id);
            if (producto.isPresent()) {
                logger.info("Producto encontrado con ID: {}", id);
            } else {
                logger.warn("No se encontró producto con ID: {}", id);
            }
            return producto;
        } catch (Exception e) {
            logger.error("Error al buscar producto por ID {}: {}", id, e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Producto> findByCodigo(String codigo) {
        logger.info("Buscando producto por código: {}", codigo);
        try {
            Optional<Producto> producto = productoRepository.findByCodProducto(codigo);
            if (producto.isPresent()) {
                logger.info("Producto encontrado con código: {}", codigo);
            } else {
                logger.warn("No se encontró producto con código: {}", codigo);
            }
            return producto;
        } catch (Exception e) {
            logger.error("Error al buscar producto por código {}: {}", codigo, e.getMessage());
            throw e;
        }
    }

    @Override
    public Producto save(Producto producto) {
        try {
            if (producto.getCodProducto() == null) {
                logger.info("Creando nuevo producto con código: {}", producto.getCodProducto());
            } else {
                logger.info("Actualizando producto con ID: {}", producto.getCodProducto());
            }
            Producto savedProducto = productoRepository.save(producto);
            logger.info("Producto guardado exitosamente con ID: {}", savedProducto.getCodProducto());
            return savedProducto;
        } catch (Exception e) {
            logger.error("Error al guardar producto: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteById(String id) {
        logger.info("Eliminando producto con ID: {}", id);
        try {
            productoRepository.deleteById(id);
            logger.info("Producto eliminado exitosamente con ID: {}", id);
        } catch (Exception e) {
            logger.error("Error al eliminar producto con ID {}: {}", id, e.getMessage());
            throw e;
        }
    }
} 