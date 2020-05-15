package SYL.Controllers;

import SYL.App.BeanConfig;
import SYL.Models.PlanModel;
import SYL.Services.PlanService;
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
public class PlanRestController {

    @Autowired
    private PlanService planService;

    @Autowired
    private Profiler profiler;

    @RequestMapping(value = "/plan/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") int id) {
        PlanModel plan = null;

        try (ProfiledCall call = profiler.profiledCall("get_plan_by_id")) {
            call.start();
            plan = planService.getPlanByID(id);
            call.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("PlanInfo").addObject("plan", plan);
    }

    @RequestMapping(value = "/plans/get_all", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPlans() {
        List<PlanModel> plans = new ArrayList<>();

        try (ProfiledCall call = profiler.profiledCall("get_all_plans")) {
            call.start();
            plans = planService.getAll();
            call.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ModelAndView("AllPlans").addObject("plans", plans);
    }

    @RequestMapping(value = "/plan/addPrivelege/{id}/{privelege}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addPrivelege(@PathVariable("id") int id,
                                     @PathVariable("privelege") String privelege) {

        PlanModel plan;

        try (ProfiledCall call = profiler.profiledCall("get_plan_by_id")) {
            call.start();
            plan = planService.getPlanByID(id);
            call.stop();
        }

        try (ProfiledCall call = profiler.profiledCall("add_privelege")) {
            call.start();
            planService.addPrivelege(plan, privelege);
            call.stop();
        }

        return new ModelAndView("PlanInfo").addObject("plan", plan);
    }

    @RequestMapping(value = "/plan/removePrivelege/{id}/{privelege}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView removePrivelege(@PathVariable("id") int id,
                                        @PathVariable("privelege") String privelege) {
        PlanModel plan;

        try (ProfiledCall call = profiler.profiledCall("get_plan_by_id")) {
            call.start();
            plan = planService.getPlanByID(id);
            call.stop();
        }

        try (ProfiledCall call = profiler.profiledCall("delete_privelege")) {
            call.start();
            planService.deletePrivelege(plan, privelege);
            call.stop();
        }

        return new ModelAndView("PlanInfo").addObject("plan", plan);
    }

    @RequestMapping(value = "/plan/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePlan(@PathVariable("id") int id) {
        try (ProfiledCall call = profiler.profiledCall("delete_plan_by_id")) {
            call.start();
            planService.deletePlan(id);
            call.stop();
        }
        return new ModelAndView("redirect:/plans/get_all");
    }

    @RequestMapping(value = "/addPlan", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        return new ModelAndView("addPlan").addObject("plan", new PlanModel());
    }

    @RequestMapping(value = "/addPlanProcess", method = RequestMethod.POST)
    public String addPlan(@ModelAttribute("plan") PlanModel plan) {
        try (ProfiledCall call = profiler.profiledCall("save_plan")) {
            call.start();
            planService.savePlan(plan);
            call.stop();
        }
        return "success";
    }

}
