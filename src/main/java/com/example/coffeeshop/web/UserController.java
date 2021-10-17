package com.example.coffeeshop.web;

import com.example.coffeeshop.model.binding.UserLoginBindModel;
import com.example.coffeeshop.model.binding.UserRegBindModel;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.userService = userService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        if (!model.containsAttribute("userRegBindModel")) {
            model.addAttribute("userRegBindModel", new UserRegBindModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegBindModel userRegBindModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // 1. check for @Valid input
        if (bindingResult.hasErrors() || !userRegBindModel.getPassword().equals(userRegBindModel.getConfirmPassword())
                || userService.checkExistingUsername(userRegBindModel.getUsername())) {
            redirectAttributes.addFlashAttribute("userRegBindModel", userRegBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegBindModel", bindingResult);
            // 2. check for existing username
            if (userService.checkExistingUsername(userRegBindModel.getUsername())) {
                redirectAttributes.addFlashAttribute("existUsername", true);
            }
            return "redirect:/users/register";
        }

        // 3. save/register the new User to DB
        userService.registerNewUser(modelMapper.map(userRegBindModel, UserServiceModel.class));
        return "redirect:/users/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        if (!model.containsAttribute("userLoginBindModel")) {
            model.addAttribute("userLoginBindModel", new UserLoginBindModel());
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginBindModel userLoginBindModel,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindModel", userLoginBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindModel", bindingResult);
            return "redirect:/users/login";
        }
        if (!userService.authenticate(userLoginBindModel.getUsername(), userLoginBindModel.getPassword())) {
            redirectAttributes.addFlashAttribute("userLoginBindModel", userLoginBindModel)
                    .addFlashAttribute("notFound", true);
            return "redirect:/users/login";
        }
        userService.login(userLoginBindModel.getUsername());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
