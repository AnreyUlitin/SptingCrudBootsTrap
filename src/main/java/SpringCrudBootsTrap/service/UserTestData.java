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
    public void loadTestUser() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");
        roleService.addRole(role1);
        roleService.addRole(role2);

        User user1 = new User("first_user_name_1", "last_user_name_1", 26,"user1@bk", "user1");
        User user2 = new User("first_user_name_2", "last_user_name_2", 27,"user2@bk", "user2");
        User user3 = new User("first_user_name_3", "last_user_name_3", 28,"user3@bk", "user3");
        User user4 = new User("first_user_name_4", "last_user_name_4", 29,"user4@bk", "user4");
        User user5 = new User("first_user_name_5", "last_user_name_5", 30,"user5@bk", "user5");
        User user6 = new User("first_user_name_6", "last_user_name_6", 31,"user6@bk", "user6");
        user1.setRoles(Set.of(role1, role2));
        user2.setRoles(Set.of(role1));
        user3.setRoles(Set.of(role1));
        user4.setRoles(Set.of(role2));
        user5.setRoles(Set.of(role2));
        user6.setRoles(Set.of(role2,role1));
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        userService.addUser(user5);
        userService.addUser(user6);
    }
}
