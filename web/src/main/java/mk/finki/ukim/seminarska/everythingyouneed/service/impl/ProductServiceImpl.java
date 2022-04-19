package mk.finki.ukim.seminarska.everythingyouneed.service.impl;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.Company;
import mk.finki.ukim.seminarska.everythingyouneed.model.Product;
import mk.finki.ukim.seminarska.everythingyouneed.model.dto.ProductDto;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.CategoryNotFoundException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.CompanyNotFoundException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.ProductNotFoundException;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.CategoryRepository;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.CompanyRepository;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.ProductRepository;
import mk.finki.ukim.seminarska.everythingyouneed.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CompanyRepository companyRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> listAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, String description, int quantity, float price, Long categoryId, Long companyId) {
            Category category = this.categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException(categoryId));
            Company company = this.companyRepository.findById(companyId)
                    .orElseThrow(() -> new CompanyNotFoundException(companyId));
            this.productRepository.deleteByName(name);

        return Optional.of(this.productRepository.save(new Product(name, description, quantity, price, category, company)));
    }

    @Override
    public Optional<Product> save( ProductDto productDto) {
        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Company company = this.companyRepository.findById(productDto.getCompany())
                .orElseThrow(() -> new CompanyNotFoundException(productDto.getCompany()));
        this.productRepository.deleteByName(productDto.getName());

        return Optional.of(this.productRepository.save(new Product(productDto.getName(), productDto.getDescription(),
                productDto.getQuantity(), productDto.getPrice(), category, company)));

    }

    @Override
    public Optional<Product> edit(Long id, ProductDto productDto) {
        Product p = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        p.setName(productDto.getName());
        p.setDescription(productDto.getDescription());
        p.setQuantity(productDto.getQuantity());
        p.setPrice(productDto.getPrice());

        Category category = this.categoryRepository.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Company company = this.companyRepository.findById(productDto.getCompany())
                .orElseThrow(() -> new CompanyNotFoundException(productDto.getCompany()));

        p.setCategory(category);
        p.setCompany(company);

        return Optional.of(this.productRepository.save(p));
    }

    @Override
    @Transactional
    // transactional poshto sakame vo
    // ista transakcija da se izvrshi celiot kod t.e atomi4na operacija
    public Optional<Product> edit(Long id, String name, String description, int quantity, float price, Long categoryId, Long companyId) {

        Product p = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        p.setName(name);
        p.setDescription(description);
        p.setQuantity(quantity);
        p.setPrice(price);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Company company = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CompanyNotFoundException(companyId));
        this.productRepository.deleteByName(name);

        p.setCategory(category);
        p.setCompany(company);

        return Optional.of(this.productRepository.save(p));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
