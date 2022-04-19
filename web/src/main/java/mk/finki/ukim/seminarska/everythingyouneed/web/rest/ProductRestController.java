package mk.finki.ukim.seminarska.everythingyouneed.web.rest;

import mk.finki.ukim.seminarska.everythingyouneed.model.Product;
import mk.finki.ukim.seminarska.everythingyouneed.model.dto.ProductDto;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.ProductRepository;
import mk.finki.ukim.seminarska.everythingyouneed.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductRestController {

    private final ProductService productService;


    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findAll() {
        return this.productService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return this.productService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Product> add(@RequestBody ProductDto productDto) {
        return this.productService.save(productDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return this.productService.edit(id, productDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable long id){
            this.productService.deleteById(id);
            if(this.productService.findById(id).isEmpty()) return ResponseEntity.ok().build();
            return ResponseEntity.badRequest().build();
    }
}
