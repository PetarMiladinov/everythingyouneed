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

    //@OneToMany
   // private List<Product> products; // mesto da chuvame lista od site produkti od edna kompanija
    //mozhime za sekoj produkt da chuvame ushte eden atribut koj kje bidi Kompanija

    public Company(){}
    public Company(String name, String address, String description, List<Category> categories) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.categories = categories;
    }
}
