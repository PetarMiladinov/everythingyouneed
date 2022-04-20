package mk.finki.ukim.seminarska.everythingyouneed.model;

import lombok.Data;
import mk.finki.ukim.seminarska.everythingyouneed.model.enumerations.ShoppingCartStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userId;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;

    public ShoppingCart() {
    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
