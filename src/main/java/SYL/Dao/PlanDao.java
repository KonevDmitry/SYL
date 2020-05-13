package SYL.Dao;

import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import SYL.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlanDao {
    public void save(PlanModel plan) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(plan);
        tx1.commit();
        session.close();
    }

    public PlanModel getByID(long id) {
        return (PlanModel) HibernateSessionFactoryUtil.getSessionFactory().openSession().get(PlanModel.class, id);
    }

    public void delete(PlanModel plan) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        for (UserModel user : plan.getUsers()) {
            user.setPlan(null);
            session.saveOrUpdate(user);
        }
        session.flush();
        plan.getUsers().clear();
        session.delete(session.load(PlanModel.class, plan.getId()));
        tx1.commit();
        session.close();
    }

    public void update(PlanModel plan) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(plan);
        tx1.commit();
        session.close();
    }

    public List<PlanModel> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        List<PlanModel> res = session.createNativeQuery("SELECT p.id, p.desc, p.cost" +
                " FROM sylschema.plans p", PlanModel.class)
                .getResultList();
        return res;
    }
}
