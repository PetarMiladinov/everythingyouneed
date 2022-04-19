package mk.finki.ukim.seminarska.everythingyouneed.service.impl;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.CategoryAlreadyExistsException;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.CategoryRepository;
import mk.finki.ukim.seminarska.everythingyouneed.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    //Dependency injection
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(String name) {
        if(name.isEmpty() || name==null){
            throw new IllegalArgumentException();
        }
        if(categoryRepository.findByNameLike(name)){
            throw new CategoryAlreadyExistsException(name);
        }
        Category c = new Category(name);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public Category update(String name) {
        if((name.isEmpty() || name==null)&&!categoryRepository.findByNameLike(name)){
            throw new IllegalArgumentException();
        }
        Category c = new Category(name);
        categoryRepository.save(c);
        return c;
    }

    @Override
    public void delete(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException();
        }
        categoryRepository.deleteByName(name);
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String searchText) {
        return categoryRepository.findAllByNameLike(searchText);
    }
}
