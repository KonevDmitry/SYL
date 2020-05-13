package SYL.Dao;

import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import SYL.Utils.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import java.util.List;


public class UserDao {

    public UserModel getByID(long id) {
        return (UserModel) HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UserModel.class, id);
    }

    public void save(UserModel user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void updateInfo(UserModel user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(UserModel user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        PlanModel userPlan = user.getPlan();

        userPlan.getUsers().remove(user);
        user.setPlan(null);

        session.delete(user);

        tx1.commit();
        session.close();

    }

    public List<UserModel> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        List<UserModel> res = session.createNativeQuery("SELECT u.id, u.name, " +
                "u.surname, u.email, u.password, u.plan_id FROM sylschema.users u", UserModel.class)
                .getResultList();
        return res;
    }

    public int login(String email, String password) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Object userID;
        try {
            userID = session.createNativeQuery("SELECT u.id FROM sylschema.users u" +
                    " WHERE u.email LIKE ?1").setParameter(1, email)
                    .getSingleResult();
            UserModel user = getByID((Long.parseLong(userID.toString())));
            if (user.getPassword().equals(password))
                return 1;
            else
                return 0;
        } catch (NoResultException nre) {
            return -1;
        }
    }

    public void updatePlan(UserModel user, PlanModel newPlan) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        PlanModel userPlan = user.getPlan();

        userPlan.getUsers().remove(user);
        user.setPlan(newPlan);
        newPlan.getUsers().add(user);
        session.merge(user);
        tx1.commit();
        session.close();
    }
}