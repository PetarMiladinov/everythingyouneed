package mk.finki.ukim.seminarska.everythingyouneed.repository.impl;

import mk.finki.ukim.seminarska.everythingyouneed.bootstrap.DataHolder;
import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.CategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryCategoryRepository {

    public List<Category> findAll(){ return DataHolder.categories;
    }

    public Category save(Category c){
        if (c==null || c.getName().isEmpty()) {
            return null;
        }
        DataHolder.categories.removeIf(r->r.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }

    public Optional<Category> findById(Long id){
        return DataHolder.categories.stream().filter(r->r.getId().equals(id)).findFirst();
    }
}
