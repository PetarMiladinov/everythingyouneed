package mk.finki.ukim.seminarska.everythingyouneed.web.controllers;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.Company;
import mk.finki.ukim.seminarska.everythingyouneed.model.Product;
import mk.finki.ukim.seminarska.everythingyouneed.service.CategoryService;
import mk.finki.ukim.seminarska.everythingyouneed.service.CompanyService;
import mk.finki.ukim.seminarska.everythingyouneed.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final CompanyService companyService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             CompanyService companyService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.companyService = companyService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.listAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Company> companies = this.companyService.listAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("companies", companies);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProductPage(Model model) {
        List<Company> companies = this.companyService.listAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("companies", companies);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Integer quantity,
            @RequestParam float price,
            @RequestParam Long category,
            @RequestParam Long company) {
        if (id != null) {
            this.productService.edit(id, name, description, quantity,price, category, company);
        } else {
            this.productService.save(name, description, quantity,price, category, company);
        }
        return "redirect:/products";
    }
}
