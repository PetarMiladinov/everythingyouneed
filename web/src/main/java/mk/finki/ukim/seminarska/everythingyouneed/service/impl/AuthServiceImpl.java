package mk.finki.ukim.seminarska.everythingyouneed.service.impl;


import mk.finki.ukim.seminarska.everythingyouneed.model.User;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.UserRepository;
import mk.finki.ukim.seminarska.everythingyouneed.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username==null || username.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
