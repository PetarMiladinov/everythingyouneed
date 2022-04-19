package mk.finki.ukim.seminarska.everythingyouneed.service.impl;

import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.Company;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.CompanyNotFoundException;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.CompanyRepository;
import mk.finki.ukim.seminarska.everythingyouneed.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company create(String name, String address, String description,
                          Category category) {

        Company c = new Company(name, address, description, category);
        return this.companyRepository.save(c);
    }

    @Override
    public Optional<Company> update(Long id,String name, String address, String description, Category category) {
        Company c = this.companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        c.setName(name);
        c.setAddress(address);
        c.setDescription(description);
        c.setCategory(category);
        return Optional.of(this.companyRepository.save(c));
    }

    @Override
    public List<Company> listAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.companyRepository.deleteById(id);
    }


    @Override
    public Optional<Company> findById(Long id) {
        return this.companyRepository.findById(id);
    }

    @Override
    public List<Company> searchCompaniesByCategory(Category c) {
        return this.companyRepository.findCompaniesByCategory(c);
    }
}
