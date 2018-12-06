package pl.ines.shipcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.ines.shipcompany.model.Product;
import pl.ines.shipcompany.model.User;
import pl.ines.shipcompany.repository.ProductRepository;
import pl.ines.shipcompany.repository.UserRepository;
import pl.ines.shipcompany.service.ProductService;
import pl.ines.shipcompany.service.UserService;

@Controller
public class HomeController {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private UserService userService;
    private ProductService productService;

    @Autowired
    public HomeController(ProductRepository productRepository, UserRepository userRepository, UserService userService, ProductService productService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.productService = productService;
    }


    @GetMapping("/")
    public String home(Model model){
        //productRepository.findAll();
        model.addAttribute("products",productRepository.findAll());
        return "homePage";
    }

    @GetMapping("/newproduct")
        public String newProductMethod(Model model1){
        userRepository.findAll();
        model1.addAttribute("users", userRepository.findAll());
        model1.addAttribute("newProduct",new Product());
        return "/newproduct";
    }

    @GetMapping("/edituser")
    public String editUser(Model model){
        model.addAttribute("users",userRepository.findAll());
        model.addAttribute("editUser",new User());
        return "/edituser";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(Model model){
    model.addAttribute("users",userRepository.findAll());
        return "/deleteuser";
    }
    @PostMapping("/deletinguser")
    public String deletingUser(@RequestParam Long index){
        User user=userRepository.getOne(index);
        if(userRepository.existsById(index)){
            if(user.getProducts().isEmpty()){
                userRepository.deleteById(index);
                userRepository.findAll();
                return "redirect:/";
            }
            else{
                return "redirect:/cantdelete.html";
            }
        }
        else {
            return "redirect:/noid.html";
        }
    }

    @PostMapping("/editinguser")
    public String editingUser(User user){
        userService.update(user.getFirstname(),user.getLastname(),user.getId());
        return "redirect:/";
    }

    @GetMapping("/newuser")
    public String newUserMethod(Model model){
        model.addAttribute("newUser",new User());
        return "/newuser";
    }

    @PostMapping("/adding")
    public String formMethod(Product product){
        productRepository.save(product);
    return "redirect:/";
    }

    @PostMapping("/addingUser")
    public String addingUser(User user){
        userRepository.save(user);
        return "redirect:/";
    }

    @PostMapping("/deleting")
    public String deletingMethod(@RequestParam Long index){
        System.out.println(index.toString());
        productRepository.deleteById(index);
        return "redirect:/";
    }

    @PostMapping("/deletion")
    public String deletionMethod(@RequestParam String indexOfProd, Model model){
        model.addAttribute("indexOfProd",indexOfProd);
        return "/deletion";
    }

    @PostMapping("/editing")
    public String editionMethod(Product product){
        productService.update(product.getId(),product.getThickness(),product.getWidth(),product.getLength(),product.getQuantity(),product.getGrade(),product.getTolerance());
        return "redirect:/";
    }

    @PostMapping("/edition")
    public String editionPostMethod(@RequestParam Long productID, Model model){
        model.addAttribute("editProduct",productRepository.getOne(productID));
        return "/edition";
    }
    @PostMapping("/showAll")
    public String showAll(){
        return "redirect:/";
    }

    @PostMapping("/sorting")
    public String sortingMethod(@RequestParam String option){
        switch (option){
            case "0": productRepository.orderById();
                break;
            case "1": productRepository.orderByThickness();
                break;
            case "2": productRepository.orderByWidth();
                break;
            case "3": productRepository.orderByLength();
                break;
            case "4": productRepository.orderByQuantity();
                break;
            case "5": productRepository.orderByGrade();
                break;
            case "6": productRepository.orderByTolerance();
                break;
        }
        return "redirect:/";
    }

    @PostMapping("/filtering")
    public String filteringMethod(@RequestParam String border,@RequestParam String radio){

        int value=Integer.parseInt(border);
        switch (radio){
            case "1": productRepository.getProductsWhereThicknessIs(value);
                break;
            case "2": productRepository.getProductsWhereWidthIs(value);
                break;
            case "3": productRepository.getProductsWhereLengthIs(value);
                break;
            case "4": productRepository.getProductsWhereQuantityIs(value);
                break;
        }
        return "redirect:/";
    }
}
