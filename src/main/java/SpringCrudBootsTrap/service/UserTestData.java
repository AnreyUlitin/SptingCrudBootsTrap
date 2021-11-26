package SpringCrudBootsTrap.service;

import SpringCrudBootsTrap.model.*;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Component
public class UserTestData {

    RoleService roleService;
    UserService userService;

    @Autowired
    public UserTestData(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void loadDataUser() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roleService.addRole(role1);
        roleService.addRole(role2);

        User user1 = new User("user1", "user1", "first_user_name_1", "last_user_name_1", "26", "user_mail_1@bk.com");
        User user2 = new User("user2", "user2", "first_user_name_2", "last_user_name_2", "27", "user_mail_2@bk.com");
        User user3 = new User("user3", "user3", "first_user_name_3", "last_user_name_3", "28", "user_mail_3@bk.com");
        user1.setRoles(Set.of(role1, role2));
        user2.setRoles(Set.of(role1, role2));
        user3.setRoles(Set.of(role1, role2));
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
    }
}
