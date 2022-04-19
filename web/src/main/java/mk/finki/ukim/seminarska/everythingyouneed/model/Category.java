package mk.finki.ukim.seminarska.everythingyouneed.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToMany(mappedBy ="category", fetch = FetchType.LAZY)
    private List<Company> company;

    private String name;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }
}
