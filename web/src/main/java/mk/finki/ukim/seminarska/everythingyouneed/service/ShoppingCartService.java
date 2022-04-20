package mk.finki.ukim.seminarska.everythingyouneed.service;

import mk.finki.ukim.seminarska.everythingyouneed.model.Product;
import mk.finki.ukim.seminarska.everythingyouneed.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Product> listAllProductsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);

}
