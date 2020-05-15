package SYL.Controllers;

import SYL.Models.PlanModel;
import SYL.Services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.fix.aggregating.profiler.ProfiledCall;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlanRestController {

    @Autowired
    private PlanService planService;

    @RequestMapping(value = "/plan/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") int id) {
        return new ModelAndView("PlanInfo")
                .addObject("plan", planService.getPlanByID(id));
    }

    @RequestMapping(value = "/plans/getAll", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPlans() {
        return new ModelAndView("AllPlans")
                .addObject("plans", planService.getAll());
    }

    @RequestMapping(value = "/plan/addPrivelege/{id}/{privelege}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addPrivelege(@PathVariable("id") int id,
                                     @PathVariable("privelege") String privelege) {

        PlanModel plan = planService.getPlanByID(id);
        planService.addPrivelege(plan, privelege);

        return new ModelAndView("PlanInfo").addObject("plan", plan);
    }

    @RequestMapping(value = "/plan/removePrivelege/{id}/{privelege}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView removePrivelege(@PathVariable("id") int id,
                                        @PathVariable("privelege") String privelege) {
        PlanModel plan = planService.getPlanByID(id);
        planService.deletePrivelege(plan, privelege);

        return new ModelAndView("PlanInfo").addObject("plan", plan);
    }

    @RequestMapping(value = "/plan/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePlan(@PathVariable("id") int id) {
        planService.deletePlan(id);
        return new ModelAndView("redirect:/plans/getAll");
    }

    @RequestMapping(value = "/addPlan", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        return new ModelAndView("addPlan").addObject("plan", new PlanModel());
    }

    @RequestMapping(value = "/addPlanProcess", method = RequestMethod.POST)
    public String addPlan(@ModelAttribute("plan") PlanModel plan) {
        planService.savePlan(plan);
        return "success";
    }

}
