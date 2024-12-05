package controllers;

import models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.SaleService;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {
    @Autowired
    private
    SaleService saleService;

    @PostMapping
    public ResponseEntity<Sale> recordSale(@RequestBody Sale sale) {
        Sale registeredSale = saleService.recordSale(sale);
        return ResponseEntity.ok(registeredSale);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Sale>> getSalesByProductId(@PathVariable Long productId) {
        List<Sale> sales = saleService.getSalesByProductId(productId);
        return ResponseEntity.ok(sales);
    }
}
