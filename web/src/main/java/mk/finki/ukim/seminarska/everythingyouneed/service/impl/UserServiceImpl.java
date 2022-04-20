package mk.finki.ukim.seminarska.everythingyouneed.service.impl;

import mk.finki.ukim.seminarska.everythingyouneed.model.User;
import mk.finki.ukim.seminarska.everythingyouneed.model.enumerations.Role;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.InvalidUsernameOrPasswordException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.seminarska.everythingyouneed.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.seminarska.everythingyouneed.repository.jpa.UserRepository;
import mk.finki.ukim.seminarska.everythingyouneed.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role userRole) {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if (!password.equals(repeatPassword))
            throw new PasswordsDoNotMatchException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,passwordEncoder.encode(password),name,surname,userRole);
        return userRepository.save(user);
    }
}
