package SYL.Controllers;

import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import SYL.Services.PlanService;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class PlanRestController {

    @Autowired
    private PlanService planService;


    @RequestMapping(value = "/plan/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("plan", planService.getPlanByID(id));
        mv.setViewName("PlanInfo");
        return mv;
    }

    @RequestMapping(value = "/plans/get_all", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPlans() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("plans", planService.getAll());
        mv.setViewName("AllPlans");
        return mv;
    }

    @RequestMapping(value = "/plan/addPrivelege/{id}/{privelege}",
            method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView addPrivelege(@PathVariable("id") int id,
                                     @PathVariable("privelege") String privelege) {
        PlanModel plan = planService.getPlanByID(id);
        planService.addPrivelege(plan,privelege);
        ModelAndView mv = new ModelAndView();
        mv.addObject("plan", plan);
        mv.setViewName("PlanInfo");
        return mv;
    }

    @RequestMapping(value = "/plan/removePrivelege/{id}/{privelege}",
            method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView removePrivelege(@PathVariable("id") int id,
                                     @PathVariable("privelege") String privelege) {
        PlanModel plan = planService.getPlanByID(id);
        planService.deletePrivelege(plan,privelege);
        ModelAndView mv = new ModelAndView();
        mv.addObject("plan", plan);
        mv.setViewName("PlanInfo");
        return mv;
    }

    @RequestMapping(value = "/plan/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePlan(@PathVariable("id") int id) {
        planService.deletePlan(id);
        return new ModelAndView("redirect:/plans/get_all");
    }

    @RequestMapping(value = "/addPlan", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("addPlan");
        mav.addObject("plan", new PlanModel());
        return mav;
    }

    @RequestMapping(value = "/addPlanProcess", method = RequestMethod.POST)
    public String addPlan(@ModelAttribute("plan") PlanModel plan) {
        planService.savePlan(plan);
        return "success";
    }

}
