package mk.finki.ukim.seminarska.everythingyouneed.repository.jpa;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    List<Company> findCompaniesByCategories(Category categoryName);
    void deleteById(Long id);
}
