package mk.finki.ukim.seminarska.everythingyouneed.service.impl;

import mk.finki.ukim.seminarska.everythingyouneed.model.Product;
import mk.finki.ukim.seminarska.everythingyouneed.model.ShoppingCart;
import mk.finki.ukim.seminarska.everythingyouneed.model.User;
import mk.finki.ukim.seminarska.everythingyouneed.model.enumerations.ShoppingCartStatus;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.ProductNotFoundException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.ShoppingCartNotFoundException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.UserNotFoundException;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.UserRepository;
import mk.finki.ukim.seminarska.everythingyouneed.service.ProductService;
import mk.finki.ukim.seminarska.everythingyouneed.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if(shoppingCart.getProducts()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
