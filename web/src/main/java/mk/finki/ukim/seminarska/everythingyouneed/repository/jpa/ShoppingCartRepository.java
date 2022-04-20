package mk.finki.ukim.seminarska.everythingyouneed.repository.jpa;

import mk.finki.ukim.seminarska.everythingyouneed.model.ShoppingCart;
import mk.finki.ukim.seminarska.everythingyouneed.model.User;
import mk.finki.ukim.seminarska.everythingyouneed.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus shoppingCartStatus);

}
