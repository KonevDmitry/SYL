package SYL.Services;

import SYL.App.BeanConfig;
import SYL.Dao.PlanDao;
import SYL.Models.PlanModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import ru.fix.aggregating.profiler.ProfiledCall;
import ru.fix.aggregating.profiler.Profiler;

import java.util.ArrayList;
import java.util.List;

@Repository
@Import(BeanConfig.class)
public class PlanService {
    @Autowired
    private Profiler profiler;

    private final PlanDao planDAO = new PlanDao();

    public PlanModel getPlanByID(int id) {
        PlanModel plan;

        try (ProfiledCall call = profiler.profiledCall("get_plan_by_id")) {
            call.start();
            plan = planDAO.getByID(id);
            call.stop();
        }

        return plan;
    }

    public void savePlan(PlanModel plan) {
        try (ProfiledCall call = profiler.profiledCall("save_plan")) {
            call.start();
            planDAO.save(plan);
            call.stop();
        }
    }

    public void deletePlan(long id) {
        try (ProfiledCall call = profiler.profiledCall("delete_plan")) {
            call.start();
            planDAO.delete(id);
            call.stop();
        }
    }

    public void updatePlan(PlanModel plan) {
        try (ProfiledCall call = profiler.profiledCall("update_plan")) {
            call.start();
            planDAO.update(plan);
            call.stop();
        }
    }

    public List<PlanModel> getAll() {
        List<PlanModel> plans;

        try (ProfiledCall call = profiler.profiledCall("get_all_plans")) {
            call.start();
            plans = planDAO.getAll();
            call.stop();
        }

        return plans;
    }

    public void addPrivelege(PlanModel plan, String privelege) {
        try (ProfiledCall call = profiler.profiledCall("add_plan_privelege")) {
            call.start();
            planDAO.addPrivelege(plan, privelege);
            call.stop();
        }
    }

    public void deletePrivelege(PlanModel plan, String privelege) {
        try (ProfiledCall call = profiler.profiledCall("delete_plan_privelege")) {
            call.start();
            planDAO.deletePrivelege(plan, privelege);
            call.stop();
        }
    }
}
