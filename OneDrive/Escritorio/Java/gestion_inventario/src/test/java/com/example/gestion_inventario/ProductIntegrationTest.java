package com.example.gestion_inventario;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import repository.ProductRepository;
import services.ProductService;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class ProductIntegrationTest {
    @LocalServerPort
    private int port;
    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
    private ProductService service;
    private List<Product> products;


    @Test
    void testCrearTarea() {
        Product product = new Product();
        product.setName("Jugo");
        product.setPrice(2000);
        product.setQuantity(2);

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/api/tareas")
                .then()
                .statusCode(201)
                .body("name", equalTo("Jugo"))
                .body("price", equalTo(2000.0f))
                .body("quantity", equalTo(2));
    }

    @Test
    void testGetTarea() {
        Product productCreado = new Product();
        productCreado.setName("Jugo");
        productCreado.setPrice(2000);
        productCreado.setQuantity(2);

        Integer tareaId = given()
                .contentType(ContentType.JSON)
                .body(productCreado)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when()
                .get("/api/products/{id}", tareaId)
                .then()
                .statusCode(200)
                .body("id", equalTo(tareaId))
                .body("name", equalTo("Jugo"))
                .body("price", equalTo(2000.0f))
                .body("quantity", equalTo(2));
    }

    @Test
    void testUpdateTarea() {
        Product productCreado = new Product();
        productCreado.setName("Jugo");
        productCreado.setPrice(2000);
        productCreado.setQuantity(2);
        Integer tareaId = given()
                .contentType(ContentType.JSON)
                .body(productCreado)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract().path("id");

        productCreado.setName("Miel");
        productCreado.setPrice(4000.0f);
        productCreado.setQuantity(6);

        given()
                .contentType(ContentType.JSON)
                .body(productCreado)
                .when()
                .put("/api/products/{id}", tareaId)
                .then()
                .statusCode(200)
                .body("id", equalTo(tareaId))
                .body("name", equalTo("Miel"))
                .body("price", equalTo(4000.0f))
                .body("quantity", equalTo(6));    }

    @Test
    void testDeleteTarea() {
        Product productCreado = new Product();
        productCreado.setName("Jugo");
        productCreado.setPrice(2000);
        productCreado.setQuantity(2);

        Integer tareaId = given()
                .contentType(ContentType.JSON)
                .body(productCreado)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when()
                .delete("/api/products/{id}", tareaId)
                .then()
                .statusCode(200);
    }

}
