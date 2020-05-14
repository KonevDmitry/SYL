package SYL.Controllers;

import SYL.Models.Login;
import SYL.Models.UserModel;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(Model model) {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("Login", new Login());
        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(@ModelAttribute("login") Login logForm) {
        ModelAndView mav = null;
        int res = userService.login(logForm.getEmail(), logForm.getPassword());
        if (res == 1) {
            UserModel user = userService.loggedUser(logForm.getEmail());
            mav = new ModelAndView("welcome");
            mav.addObject("firstname", user.getName());
        } else if (res == 0) {
            mav = new ModelAndView("wrongPassword");
        } else {
            mav = new ModelAndView("notRegistered");
        }
        return mav;
    }
}
