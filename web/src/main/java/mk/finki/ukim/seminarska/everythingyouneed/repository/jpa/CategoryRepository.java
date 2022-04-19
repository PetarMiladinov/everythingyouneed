package mk.finki.ukim.seminarska.everythingyouneed.repository.jpa;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllByNameLike(String text);
    List<Category> findAll();
    boolean findByNameLike(String text);
    void deleteByName(String name);
}
