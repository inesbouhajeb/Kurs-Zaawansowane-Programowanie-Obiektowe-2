package pl.ines.shipcompany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ines.shipcompany.model.Product;
import pl.ines.shipcompany.repository.ProductRepository;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void update(Long id, int thickness, int width, int length, int quantity, String grade, String tolerance){
        Optional<Product> byId=productRepository.findById(id);
        Product product=byId.get();
        product.setThickness(thickness);
        product.setWidth(width);
        product.setLength(length);
        product.setQuantity(quantity);
        product.setGrade(grade);
        product.setTolerance(tolerance);
    }
}
