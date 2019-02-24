package pl.ines.shipcompany.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.ines.shipcompany.model.Product;
import pl.ines.shipcompany.model.User;
import pl.ines.shipcompany.model.UserRole;
import pl.ines.shipcompany.repository.ProductRepository;
import pl.ines.shipcompany.repository.UserRepository;
import pl.ines.shipcompany.repository.UserRoleRepository;
import pl.ines.shipcompany.service.ProductService;
import pl.ines.shipcompany.service.UserRoleService;
import pl.ines.shipcompany.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private UserService userService;
    private UserRoleService userRoleService;
    private ProductService productService;
    private List<Product> products;
    private List<User> users;

    @Autowired
    public HomeController(ProductRepository productRepository, UserRepository userRepository, UserRoleRepository userRoleRepository, UserService userService, UserRoleService userRoleService, ProductService productService) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.productService = productService;
        products=productRepository.findAll();
        users=userRepository.findAll();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }


    @GetMapping("/")
    public String home(Model model, Principal principal){
        //productRepository.findAll();
        model.addAttribute("products",products);
        model.addAttribute("actualUser",principal.getName());
        return "homePage";
    }

    @GetMapping("/newproduct")
        public String newProductMethod(Model model1){
        users=userRepository.findAll();
        model1.addAttribute("users", users);
        model1.addAttribute("newProduct",new Product());
        return "/newproduct";
    }

//    @GetMapping("/edituser")
//    public String editUser(Model model){
//    model.addAttribute("users",users);
//    return "edituser";
//    }

    @GetMapping("/edituser")
    public String editUser(Model model){
        model.addAttribute("users",users);
        model.addAttribute("editUser",new User());
        return "/edituser";
    }

    @GetMapping("/deleteuser")
    public String deleteUser(Model model){
    model.addAttribute("users",users);
        return "/deleteuser";
    }
    @PostMapping("/deletinguser")
    public String deletingUser(@RequestParam Long index){
        User user=userRepository.getOne(index);
        if(userRepository.existsById(index)){
            if(user.getProducts().isEmpty()){
                userRepository.deleteById(index);
                users=userRepository.findAll();
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
        userService.update(user);
        return "redirect:/";
    }

    @GetMapping("/newuser")
    public String newUserMethod(Model model){
        model.addAttribute("newUser",new User());
        return "/newuser";
    }

    @PostMapping("/adding")
    public String formMethod(Product product, Principal principal){
        UserRole princUserRole=userRoleRepository.findUserByUsername(principal.getName());
        Long id=princUserRole.getId();
        User princUser=userRepository.findUserById(id);
        product.setUser(princUser);
        productRepository.save(product);
        products=productRepository.findAll();
    return "redirect:/";
    }


    @PostMapping("/addingUser")
    public String addingUser(User user){
        userService.saveUser(user);
        users=userRepository.findAll();
        return "redirect:/";
    }

    @PostMapping("/deleting")
    public String deletingMethod(@RequestParam Long index){
        productRepository.deleteById(index);
        products=productRepository.findAll();
        return "redirect:/";
    }

    @PostMapping("/deletion")
    public String deletionMethod(@RequestParam String indexOfProd, Model model){
        System.out.println(indexOfProd);
        model.addAttribute("indexOfProd",indexOfProd);
        return "/deletion";
    }

    @PostMapping("/editing")
    public String editionMethod(Product product){
        productService.update(product.getId(),product.getThickness(),product.getWidth(),product.getLength(),product.getQuantity(),product.getGrade(),product.getTolerance());
        products=productRepository.findAll();
        return "redirect:/";
    }

    @PostMapping("/edition")
    public String editionPostMethod(@RequestParam Long productID, Model model){
        model.addAttribute("editProduct",productRepository.getOne(productID));
        return "/edition";
    }
    @PostMapping("/showAll")
    public String showAll(){
        products=productRepository.findAll();
        return "redirect:/";
    }

   @GetMapping("/changePassword")
    public String passwordChange() {
        return "/changePassword";
    }

    @PostMapping("/changePassword")
    public String changingPassword(@RequestParam String password,Principal principal){
        userService.updatePassword(password,principal);
       // userRoleService.updatePassword(password,principal.getName());
        return "redirect:/";
    }

    @PostMapping("/sorting")
    public String sortingMethod(@RequestParam String option){
        switch (option){
            case "0": products=productRepository.orderById();
                break;
            case "1": products=productRepository.orderByThickness();
                break;
            case "2": products=productRepository.orderByWidth();
                break;
            case "3": products=productRepository.orderByLength();
                break;
            case "4": products=productRepository.orderByQuantity();
                break;
            case "5": products=productRepository.orderByGrade();
                break;
            case "6": products=productRepository.orderByTolerance();
                break;
        }
        return "redirect:/";
    }

    @PostMapping("/filtering")
    public String filteringMethod(@RequestParam String border,@RequestParam String radio){

        int value=Integer.parseInt(border);
        switch (radio){
            case "1": products=productRepository.getProductsWhereThicknessIs(value);
                break;
            case "2": products=productRepository.getProductsWhereWidthIs(value);
                break;
            case "3": products=productRepository.getProductsWhereLengthIs(value);
                break;
            case "4": products=productRepository.getProductsWhereQuantityIs(value);
                break;
        }
        return "redirect:/";
    }
}
