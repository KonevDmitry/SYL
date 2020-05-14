package SYL.Controllers;

import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import SYL.Services.PlanService;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", userService.getAll());
        mv.setViewName("AllUsers");
        return mv;
    }

    @RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userService.getUserByID(id));
        mv.setViewName("UserInfo");
        return mv;
    }

    @RequestMapping(value = "/user/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePerson(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users/get_all");
    }

    @RequestMapping(value = "user/updatePlan/{id}/{pid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView updatePlan(@PathVariable("id") int id, @PathVariable("pid") int pid) {
        ModelAndView mav = null;
        try {
            PlanModel plan = planService.getPlanByID(pid);
            UserModel user = userService.getUserByID(id);

            userService.updatePlan(user, plan);
            mav = new ModelAndView("UserInfo");
            mav.addObject("user", userService.getUserByID(id));
            return mav;
        } catch (Exception e) {
            mav = new ModelAndView("wrongPlanOrUser");
            return mav;
        }
    }

}