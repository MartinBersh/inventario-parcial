package com.example.gestion_inventario;

import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductUnitTest {

    private ProductService productService;
    private List<Product> mockProducts;

    @BeforeEach
    void setUp() {
        mockProducts = new ArrayList<>();
        productService = new ProductService(mockProducts);
    }
    @Test
    void testCreateTarea() {
        Product nuevoProducto = new Product();
        nuevoProducto.setName("leche");
        nuevoProducto.setPrice(1000);
        nuevoProducto.setQuantity(1);

        Product result = productService.createProduct(nuevoProducto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("leche", result.getName());
        assertEquals(1000, result.getPrice());
        assertEquals(1, result.getQuantity());
        assertEquals(1, mockProducts.size());
        assertTrue(mockProducts.contains(result));
    }

    @Test
    void testGetTareaById() {
        Product productoExistente = new Product();
        productoExistente.setId(1L);
        productoExistente.setName("leche");
        productoExistente.setPrice(1000);
        productoExistente.setQuantity(1);
        mockProducts.add(productoExistente);

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1000, result.getPrice());
        assertEquals(1, result.getQuantity());
        assertEquals(1, mockProducts.size());
    }


    @Test
    void testUpdateTarea() {
        Product productoExistente = new Product();
        productoExistente.setId(1L);
        productoExistente.setName("leche");
        productoExistente.setPrice(1000);
        productoExistente.setQuantity(1);
        mockProducts.add(productoExistente);

        Product productActualizado = new Product();
        productActualizado.setName("miel");
        productActualizado.setPrice(11000);
        productActualizado.setQuantity(2);
        Product result = productService.updateProduct(1L, productActualizado);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("miel", result.getName());
        assertEquals(11000, result.getPrice());
        assertEquals(2, result.getQuantity());
    }


    @Test
    void testDeleteTarea() {
        Product productoExistente = new Product();
        productoExistente.setId(1L);
        productoExistente.setName("leche");
        productoExistente.setPrice(1000);
        productoExistente.setQuantity(1);
        mockProducts.add(productoExistente);

        productService.deleteProduct(1L);

        assertEquals(0, mockProducts.size());
        assertFalse(mockProducts.contains(productoExistente));
    }
}
