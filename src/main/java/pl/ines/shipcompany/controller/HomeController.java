package pl.ines.shipcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ines.shipcompany.model.Product;
import pl.ines.shipcompany.repository.ProductRepository;
import javax.persistence.*;
import java.util.*;

/*
Klasa służy obsłudze przychodzących do serwera żądań.
 */
@Controller
public class HomeController {

    private ProductRepository productRepository;

    @Autowired
    public HomeController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    private List<Product> allProducts;

    @Bean
    public CommandLineRunner demo(ProductRepository productRepository){
        allProducts=productRepository.findAll();
        return null;
    }
    @GetMapping("/")
    public String home(Model model){
        EntityManager entityManager=entityManagerFactory.createEntityManager();
        EntityTransaction transaction=entityManager.getTransaction();
        transaction.begin();


        //allProducts=productRepository.findAll();
        model.addAttribute("products",allProducts);

        return "homePage";
    }

    @GetMapping("/newproduct")
        public String newProductMethod(Model model){
        model.addAttribute("newProduct",new Product());
        return "newproduct";
    }

    @PostMapping("/adding")
    public String formMethod(Product product){
        productRepository.save(product);//zapis do bazy danych
        allProducts=productRepository.findAll();
    return "redirect:/";
    }

    @PostMapping("/deleting")
    public String deletingMethod(@RequestParam Long index){
        productRepository.deleteById(index);
        allProducts=productRepository.findAll();
        return "redirect:/";
    }

    @PostMapping("/deletion")
    public String deletionMethod(@RequestParam String indexOfProd, Model model){
        model.addAttribute("indexOfProd",indexOfProd);
        return "/deletion";
    }

    @PostMapping("/editing")
    public String editionMethod(Product product){
        updateProduct(product);
        return "redirect:/";
    }

    @PostMapping("/edition")
    public String editionPostMethod(@RequestParam Long productID, Model model){
        model.addAttribute("editProduct",productRepository.getOne(productID));
        return "/edition";
    }
    @PostMapping("/showAll")
    public String showAll(){
        allProducts=productRepository.findAll();
        return "redirect:/";
    }

    @PostMapping("/sorting")
    public String sortingMethod(@RequestParam String option){
        switch (option){
            case "0": allProducts=productRepository.orderById();
                break;
            case "1": allProducts = productRepository.orderByThickness();
                break;
            case "2": allProducts = productRepository.orderByWidth();
                break;
            case "3": allProducts = productRepository.orderByLength();
                break;
            case "4": allProducts = productRepository.orderByQuantity();
                break;
            case "5": allProducts = productRepository.orderByGrade();
                break;
            case "6": allProducts = productRepository.orderByTolerance();
                break;
        }
        return "redirect:/";
    }

    @PostMapping("/filtering")
    public String filteringMethod(@RequestParam String border,@RequestParam String radio){

        if(border.equals("")||(radio.isEmpty()))// radio is empty nie dziala
            return "redirect:/";

        int value=Integer.parseInt(border);
        switch (radio){
            case "1": allProducts=productRepository.getProductsWhereThicknessIs(value);
                break;
            case "2": allProducts=productRepository.getProductsWhereWidthIs(value);
                break;
            case "3": allProducts=productRepository.getProductsWhereLengthIs(value);
                break;
            case "4": allProducts=productRepository.getProductsWhereQuantityIs(value);
                break;
        }

        return "redirect:/";
    }


    private void updateProduct(Product product){
        Product newProduct=productRepository.getOne(product.getId());
        newProduct.setThickness(product.getThickness());
        newProduct.setWidth(product.getWidth());
        newProduct.setLength(product.getLength());
        newProduct.setQuantity(product.getQuantity());
        newProduct.setGrade(product.getGrade());
        newProduct.setTolerance(product.getTolerance());
        productRepository.save(newProduct);
    }

}
