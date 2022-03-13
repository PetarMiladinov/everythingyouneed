package mk.finki.ukim.seminarska.everythingyouneed.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String description;

    @OneToMany(mappedBy = "Company")
    private List<Category> categories;

    @OneToMany
    private List<Product> products;
}
