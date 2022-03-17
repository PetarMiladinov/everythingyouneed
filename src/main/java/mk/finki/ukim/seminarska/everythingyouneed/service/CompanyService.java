package mk.finki.ukim.seminarska.everythingyouneed.service;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.Company;
import mk.finki.ukim.seminarska.everythingyouneed.model.Product;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company create(String name, String address, String description, List<Category> categoryList);

    Optional<Company> update(Long id,String name, String address, String description, List<Category> categoryList);

    void deleteById(Long id);

    List<Company> listAll();
    Optional<Company> findById(Long id);
    List<Company> searchCompaniesByCategory(Category c);

}
