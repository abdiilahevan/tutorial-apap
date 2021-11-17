package apap.tutorial.emsidi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import apap.tutorial.emsidi.model.RoleModel;
import apap.tutorial.emsidi.model.UserModel;
import apap.tutorial.emsidi.service.RoleService;
import apap.tutorial.emsidi.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model){
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);
        return "form-add-user";
    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel user, Model model){
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping(value = "/viewall")
    public String listUser(Model model){
        List<UserModel> listUser = userService.getUserList();
        model.addAttribute("listUser",listUser);
        return "viewall-user";
    }

    @GetMapping(value = "/delete/{username}")
    public String deleteCabang(
        @PathVariable String username,
        Model model
    ) {
        UserModel user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        userService.deleteUser(user);
        return "delete-user";
    }
    @GetMapping(value = "/updatePassword")
    private String updatePassword(){
        return "form-update-password";
    }

    @PostMapping(value = "/updatePassword")
    private String updateUserSubmit(@RequestParam("oldPassword") String oldPassword,
                                    @RequestParam("newPassword") String newPassword,
                                    @RequestParam("confirmedNewPassword") String confirmedNewPassword,
                                    Model model) {

        if (userService.confirmPassword(oldPassword, newPassword, confirmedNewPassword).equals("none")) {
            UserModel currentLoggedIn = userService.getUserNameLogin();
            userService.updatePassword(currentLoggedIn, newPassword);
            return "update-password-berhasil";
        }
        else if (userService.confirmPassword(oldPassword, newPassword, confirmedNewPassword).equals("update-password-berhasil")){
            UserModel currentLoggedIn = userService.getUserNameLogin();
            userService.updatePassword(currentLoggedIn, newPassword);
            return "update-password-berhasil";
        }
        else {
            String message =  userService.confirmPassword(oldPassword, newPassword, confirmedNewPassword);
            model.addAttribute("message", message);
            return "password-salah";
        }
    }
}
