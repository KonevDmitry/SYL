package SYL.Services;

import SYL.Dao.PlanDao;
import SYL.Models.PlanModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanService {
    private PlanDao planDAO = new PlanDao();

    public PlanModel getPlanByID(int id) {
        return planDAO.getByID(id);
    }

    public void savePlan(PlanModel plan) {
        planDAO.save(plan);
    }

    public void deletePlan(PlanModel plan) {
        planDAO.delete(plan);
    }

    public void updatePlan(PlanModel plan) {
        planDAO.update(plan);
    }

    public List<PlanModel> getAll(){
        return planDAO.getAll();
    }
}
