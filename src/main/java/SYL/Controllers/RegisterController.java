package SYL.Controllers;

import SYL.App.BeanConfig;
import SYL.Models.UserModel;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.fix.aggregating.profiler.ProfiledCall;
import ru.fix.aggregating.profiler.Profiler;

@RestController
@Import(BeanConfig.class)
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private Profiler profiler;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        return new ModelAndView("register").addObject("user", new UserModel());
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") UserModel user) {
        try (ProfiledCall call = profiler.profiledCall("register_process")) {
            call.start();
            userService.saveUser(user);
            call.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("welcome", "firstname", user.getName());
    }
}
