package HomeWork11.services;

import HomeWork11.Entity.User;
import HomeWork11.repository.UserRepository;

/**
 * Created by ivan on 6/13/17.
 */
public class SigninService {

    UserRepository userRepository;

    public boolean register(final User user) {

        return userRepository.addUser(user);
    }

}
