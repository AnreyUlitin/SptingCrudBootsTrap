package SpringCrudBootsTrap.controller;

import SpringCrudBootsTrap.model.*;
import SpringCrudBootsTrap.service.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    public String userInfoPages(@AuthenticationPrincipal User user, Model model) {
        System.out.println("/user_1");
        model.addAttribute("user", user);
        System.out.println("/user_2");
        model.addAttribute("roles", user.getRoles());
        System.out.println("/user");
        return "user";
    }

    @GetMapping(value = "/admin")
    public String listAllUsers(@AuthenticationPrincipal User user, Role role, Model model) {

        System.out.println("/admin_1");
        model.addAttribute("user", user);
        System.out.println("/admin_2");

        model.addAttribute("allUsers", userService.getAllUsers());
        System.out.println("/admin_3");

        model.addAttribute("allRoles", roleService.getAllRoles());

        System.out.println("/admin_4");
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
    public String createUser(@ModelAttribute User user, @RequestParam(defaultValue = "boxRoles") String[] boxRoles) {

        System.out.println(user.getRoles());
        Set<Role> roleSet = new HashSet<>();
        System.out.println("/admin/create_1");
        for (String role : boxRoles) {
            System.out.println("/admin/create_2");
            roleSet.add(roleService.getRoleByRole(role));
            System.out.println("/admin/create_3");
        }
        System.out.println("/admin/create_4");
        user.setRoles(roleSet);
        System.out.println("/admin/create_5");
        userService.addUser(user);
        System.out.println("/admin/create_6");
        return "redirect:/admin";
    }


    @GetMapping(value = "/edit/{id}")
    public String editUserForm(@AuthenticationPrincipal User user, @PathVariable long id, Model model) {
        model.addAttribute("user", user);
        System.out.println("/edit/{id}_1");
        model.addAttribute("user", userService.getUserById(id));
        System.out.println("/edit/{id}_2");
        model.addAttribute("roles", roleService.getAllRoles());
        System.out.println("/edit/{id}_3");
        return "edit";
    }

    @PutMapping(value = "/edit/{id}")
    public String update(@ModelAttribute User user, @RequestParam(defaultValue = "updateRoles") String[] updateRoles) {
        System.out.println("/edit_1");
        Set<Role> roleSet = new HashSet<>();
        System.out.println("/edit_2");
        for (String roles : updateRoles) {
            roleSet.add(roleService.getRoleByRole(roles));
            System.out.println("/edit_3");

        }
        user.setRoles(roleSet);
        System.out.println("/edit_4");

        userService.updateUser(user);
        System.out.println("/edit_5");

        return "redirect:/admin";
    }


    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        System.out.println("delete/{id}");
        return "redirect:/admin";
    }

}













