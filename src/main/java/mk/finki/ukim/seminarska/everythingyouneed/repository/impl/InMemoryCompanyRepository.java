//package mk.finki.ukim.seminarska.everythingyouneed.repository.impl;
//
//import mk.finki.ukim.seminarska.everythingyouneed.bootstrap.DataHolder;
//import mk.finki.ukim.seminarska.everythingyouneed.model.Company;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class InMemoryCompanyRepository {
//
//    public List<Company> findAll(){return DataHolder.companies; }
//
//    public Company save(Company c){
//        if (c==null || c.getName().isEmpty()) {
//            return null;
//        }
//        DataHolder.companies.removeIf(r->r.getName().equals(c.getName()));
//        DataHolder.companies.add(c);
//        return c;
//    }
//
//
//}
