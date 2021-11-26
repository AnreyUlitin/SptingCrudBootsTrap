package SpringCrudBootsTrap.controller;

import SpringCrudBootsTrap.model.*;
import SpringCrudBootsTrap.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user")
    public String userInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        System.out.println("/user");
        return "user";
    }

    @GetMapping(value = "/admin")
    public String listUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        System.out.println("/admin");
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        System.out.println("/admin/new");
        return "new";
    }

    @PostMapping("/admin/create")
    public String addUser(@ModelAttribute User user, @RequestParam(defaultValue = "boxRoles") String[] boxRoles) {
        Set<Role> roleSet = new HashSet<>();
        System.out.println("roleSet");
        for (String role : boxRoles) {
            System.out.println("boxRoles");
            roleSet.add(roleService.getRoleByRole(role));
            System.out.println("массив ролей");
        }
        System.out.println("выбрать роль");
        user.setRoles(roleSet);
        userService.addUser(user);
        System.out.println("/admin/create");
        return "redirect:/admin";
    }

    @GetMapping(value = "/edit/{id}")
    public String editUserForm(@AuthenticationPrincipal User user, @PathVariable long id, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        System.out.println("/edit/{id}");
        return "edit";
    }

    @PutMapping(value = "/edit")
    public String update(@ModelAttribute User user, @RequestParam(defaultValue = "boxRoles") String[] boxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : boxRoles) {
            roleSet.add(roleService.getRoleByRole(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        System.out.println("/edit");
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        System.out.println("delete/{id}");
        return "redirect:/admin";
    }
}







