package SYL.Dao;

import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import SYL.Utils.HibernateSessionFactoryUtil;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.List;


public class UserDao {

    public UserModel getByID(long id) {
        return (UserModel) HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UserModel.class, id);
    }

    public void save(UserModel user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        if (user.getType() == null)
            user.setType("user");
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

    public void delete(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        UserModel user = getByID(id);
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
                "u.surname, u.email, u.password, u.plan_id,u.type FROM sylschema.users u", UserModel.class)
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
        if(userPlan!=null)
            userPlan.getUsers().remove(user);

        user.setPlan(newPlan);
        session.flush();

        newPlan.getUsers().add(user);
        session.merge(user);
        tx1.commit();
        session.close();
    }

    public UserModel getByEmail(String email) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        UserModel user = session.createNativeQuery("SELECT u.id, u.name, " +
                "u.surname, u.email, u.password, u.plan_id,u.type FROM sylschema.users u" +
                " WHERE u.email LIKE ?1", UserModel.class)
                .setParameter(1, email)
                .getSingleResult();
        return user;
    }
}