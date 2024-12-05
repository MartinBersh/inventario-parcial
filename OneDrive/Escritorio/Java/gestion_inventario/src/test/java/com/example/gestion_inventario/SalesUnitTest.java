package com.example.gestion_inventario;

import models.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.SaleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesUnitTest {

    private SaleService saleService;
    private List<Sale> sales;

    @BeforeEach
    void setUp() {
        sales = new ArrayList<>();
        saleService = new SaleService(sales);
    }

    @Test
    public Sale testRecordSale(Sale sale) {
        sale.setId((long) (sales.size() + 1));
        sales.add(sale);
        return sale;
    }

    @Test
    public List<Sale> TestgetSalesByProductId(String productId) {
        return sales.stream()
                .filter(sale -> sale.getProductId().equals(productId))
                .collect(Collectors.toList());
    }
}

