package mk.finki.ukim.seminarska.everythingyouneed.service;

import mk.finki.ukim.seminarska.everythingyouneed.model.User;

public interface AuthService {
    User login(String username, String password);
}

