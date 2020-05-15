package SYL.Controllers;

import SYL.App.BeanConfig;
import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import SYL.Services.PlanService;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.fix.aggregating.profiler.ProfiledCall;
import ru.fix.aggregating.profiler.Profiler;

import java.util.ArrayList;
import java.util.List;

@RestController
@Import(BeanConfig.class)
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;

    @Autowired
    private Profiler profiler;

    //ATTENTION!!! Users plans not printed to json (if you will need them), but easily could be got by getters
    // I have no idea how to edit it to json

    @RequestMapping(value = "/users/getAll", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPersons() {
        List<UserModel> users = new ArrayList<>();

        try (ProfiledCall call = profiler.profiledCall("get_all_users")) {
            call.start();
            users = userService.getAll();
            call.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("AllUsers").addObject("users", users);
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") int id) {
        UserModel user = null;

        try (ProfiledCall call = profiler.profiledCall("get_user_by_id")) {
            call.start();
            user = userService.getUserByID(id);
            call.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("UserInfo").addObject("user", user);
    }

    @RequestMapping(value = "/user/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePerson(@PathVariable("id") int id) {
        try (ProfiledCall call = profiler.profiledCall("delete_user_by_id")) {
            call.start();
            userService.deleteUser(id);
            call.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("redirect:/users/get_all");
    }

    @RequestMapping(value = "user/updatePlan/{id}/{pid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updatePlan(@PathVariable("id") int id, @PathVariable("pid") int pid) {
        try {
            PlanModel plan;
            try (ProfiledCall call = profiler.profiledCall("get_plan_by_id")) {
                call.start();
                plan = planService.getPlanByID(pid);
                call.stop();
            }

            UserModel user;

            try (ProfiledCall call = profiler.profiledCall("get_user_by_id")) {
                call.start();
                user = userService.getUserByID(id);
                call.stop();
            }

            try (ProfiledCall call = profiler.profiledCall("user_update_plan")) {
                call.start();
                userService.updatePlan(user, plan);
                call.stop();
            }

            try (ProfiledCall call = profiler.profiledCall("get_user_by_id")) {
                call.start();
                user = userService.getUserByID(id);
                call.stop();
            }

            return new ModelAndView("UserInfo").addObject("user", user);
        } catch (Exception e) {
            return new ModelAndView("wrongPlanOrUser");
        }
    }

}