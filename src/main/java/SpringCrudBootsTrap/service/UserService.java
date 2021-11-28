package SpringCrudBootsTrap.service;

import SpringCrudBootsTrap.model.*;
import java.util.List;
import java.util.Optional;


public interface UserService {

    void addUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    Optional<User> getUserById(Long id);

    User getByUsername(String username);

    User getUser(Long id);

    List<User> getAllUsers();

}

