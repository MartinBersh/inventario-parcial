package services;

import models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final List<Product> products;

    private final ProductRepository repository = null;
    private final AtomicLong counter = new AtomicLong();

    public ProductService(List<Product> products) {
        this.products = products;
    }


    public Product createProduct(Product product) {
        product.setId(counter.incrementAndGet());
        products.add(product);
        return product;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product updateProduct(Long id, Product product) {
        Optional<Product> productoExistente = products.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst();

        if (productoExistente.isPresent()) {
            Product t = productoExistente.get();
            t.setName(product.getName());
            t.setPrice(product.getPrice());
            t.setQuantity(product.getQuantity());
            return t;
        }
        return null;
    }


    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }


}


