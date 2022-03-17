package mk.finki.ukim.seminarska.everythingyouneed.service;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;

import java.util.List;

public interface CategoryService {
    Category create(String name);
    Category update (String name);
    void delete(String name);
    List<Category> listCategories();
    List<Category> searchCategories(String searchText);
}
