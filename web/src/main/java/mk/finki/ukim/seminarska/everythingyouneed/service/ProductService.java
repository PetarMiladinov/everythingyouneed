package mk.finki.ukim.seminarska.everythingyouneed.service;


import java.util.List;
import java.util.Optional;

import mk.finki.ukim.seminarska.everythingyouneed.model.Product;
import mk.finki.ukim.seminarska.everythingyouneed.model.dto.ProductDto;

public interface ProductService {
    List<Product> listAll();
    /*
     *  Koristam Optional<> za da nepram proverka dali vo sluchajov
     *  postoi Product t.e da vrati null ako nepostoi
     * */
    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name, String description, int quantity, float price, Long category, Long company);

    Optional<Product> save ( ProductDto productDto);

    Optional<Product> edit(Long id, String name, String description, int quantity, float price,Long category,Long company);

    Optional<Product> edit ( Long id, ProductDto productDto);

    void deleteById(Long id);
}
