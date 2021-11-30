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
        model.addAttribute("user", user);
        model.addAttribute("roles", user.getRoles());
        return "user";
    }

    @GetMapping(value = "/admin")
    public String listAllUsers(@AuthenticationPrincipal User user, Role role, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin";
    }

    @GetMapping(value = "/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @PostMapping("/admin/create")
    public String createUser(@ModelAttribute User user, @RequestParam(defaultValue = "boxRoles") String[] boxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String role : boxRoles) {
            roleSet.add(roleService.getRoleByRole(role));
        }
        user.setRoles(roleSet);
        userService.addUser(user);
        return "redirect:/admin";
    }


    @GetMapping(value = "/edit/{id}")
    public String editUserForm(@AuthenticationPrincipal User user, @PathVariable long id, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PutMapping(value = "/edit/{id}")
    public String update(@ModelAttribute User user, @RequestParam(defaultValue = "updateRoles") String[] boxRoles) {
        Set<Role> roleSet = new HashSet<>();
        for (String roles : boxRoles) {
            roleSet.add(roleService.getRoleByRole(roles));
        }
        user.setRoles(roleSet);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

}


















