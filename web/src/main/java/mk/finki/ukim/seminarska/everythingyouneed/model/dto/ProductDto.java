package mk.finki.ukim.seminarska.everythingyouneed.model.dto;

import lombok.Data;
import mk.finki.ukim.seminarska.everythingyouneed.model.Category;
import mk.finki.ukim.seminarska.everythingyouneed.model.Company;

@Data
public class ProductDto {
    private String name;
    private String description;
    private int quantity;
    private float price;
    private Long category;
    private Long company;

    ProductDto(){ }

    public ProductDto(String name, String description, int quantity, float price, Long category, Long company) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.company = company;
    }
}
