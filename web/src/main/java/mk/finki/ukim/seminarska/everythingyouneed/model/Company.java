package mk.finki.ukim.seminarska.everythingyouneed.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //@OneToMany
   // private List<Product> products; // mesto da chuvame lista od site produkti od edna kompanija
    //mozhime za sekoj produkt da chuvame ushte eden atribut koj kje bidi Kompanija

    public Company(){}
    public Company(String name, String address, String description, Category category) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.category = category;
    }
}
