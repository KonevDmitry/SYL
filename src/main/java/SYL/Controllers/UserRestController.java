package SYL.Controllers;

import SYL.Models.Login;
import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import SYL.Services.PlanService;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlanService planService;


    //ATTENTION!!! Users plans not printed to json, but easily could be got by getters
    // I have no idea how to edit it to json

    @RequestMapping(value = "/users/get_all", method = RequestMethod.GET, produces = {"application/json"})
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


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new UserModel());
        return mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("user") UserModel user) {
        userService.saveUser(user);
        return new ModelAndView("welcome", "firstname", user.getName());
    }

    @RequestMapping(value = "user/updatePlan/{id}/{pid}", method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView updatePlan(@PathVariable("id") int id, @PathVariable("pid") int pid) {
        ModelAndView mav = null;
        try {
            PlanModel plan = planService.getPlanByID(pid);
            UserModel user = userService.getUserByID(id);

            userService.updatePlan(user, plan);
            mav.addObject("user", userService.getUserByID(id));
            mav = new ModelAndView("UserInfo");
            return mav;
        } catch (Exception e) {
            System.out.println(e);
            mav = new ModelAndView("wrongPlanOrUser");
            return mav;
        }
    }
}