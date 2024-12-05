package services;

import models.Product;
import models.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SaleRepository;

import java.util.List;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;
    private final List<Sale> sales;

    public SaleService(List<Sale> sales) {
        this.sales = sales;
    }

    public Sale recordSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public List<Sale> getSalesByProductId(Long productId) {
        return saleRepository.findByProductId(productId);
    }
}
