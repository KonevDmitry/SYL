package SYL.Controllers;

import SYL.Models.UserModel;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.fix.aggregating.profiler.ProfiledCall;

@RestController
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        return new ModelAndView("register").addObject("user", new UserModel());
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") UserModel user) {
        userService.saveUser(user);
        return new ModelAndView("welcome", "firstname", user.getName());
    }
}
