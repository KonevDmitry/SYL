package SYL.Controllers;

import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import SYL.Services.PlanService;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.fix.aggregating.profiler.ProfiledCall;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    //ATTENTION!!! Users plans not printed to json (if you will need them), but easily could be got by getters
    // I have no idea how to edit it to json

    @RequestMapping(value = "/users/getAll", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPersons() {
        return new ModelAndView("AllUsers")
                .addObject("users", userService.getAll());
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") int id) {
        return new ModelAndView("UserInfo")
                .addObject("user", userService.getUserByID(id));
    }

    @RequestMapping(value = "/user/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePerson(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users/getAll");
    }

    @RequestMapping(value = "user/updatePlan/{id}/{pid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updatePlan(@PathVariable("id") int id, @PathVariable("pid") int pid) {
        try {
            PlanModel plan = planService.getPlanByID(pid);
            UserModel user = userService.getUserByID(id);

            userService.updatePlan(user, plan);

            return new ModelAndView("UserInfo")
                    .addObject("user", userService.getUserByID(id));
        } catch (Exception e) {
            return new ModelAndView("wrongPlanOrUser");
        }
    }

}