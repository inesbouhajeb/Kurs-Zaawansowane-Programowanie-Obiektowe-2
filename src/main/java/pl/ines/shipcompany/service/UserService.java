package pl.ines.shipcompany.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.ines.shipcompany.model.Product;
import pl.ines.shipcompany.model.User;
import pl.ines.shipcompany.repository.ProductRepository;
import pl.ines.shipcompany.repository.UserRepository;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void update(String firstName, String lastName, Long id){
    Optional<User> byId=userRepository.findById(id);
    User user=byId.get();
    user.setFirstname(firstName);
    user.setLastname(lastName);

    }
}
