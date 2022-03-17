package mk.finki.ukim.seminarska.everythingyouneed.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int quantity;
    private float price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Company company;

    public Product(){}

    public Product(String name, String description, int quantity, float price, Category category,Company company) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.category = category;
        this.company = company;
    }
}
