package SYL.Dao;

import SYL.Models.UserModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import SYL.Utils.HibernateSessionFactoryUtil;

import java.util.List;


public class UserDao {

    public UserModel findById(int id) {
        return (UserModel) HibernateSessionFactoryUtil.getSessionFactory().openSession().get(UserModel.class, id);
    }

    public void save(UserModel user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(UserModel user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(UserModel user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }


    public List<UserModel> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        List<UserModel> res = session.createNativeQuery("SELECT u.id, u.name, " +
                "u.surname, u.email, u.password FROM users u", UserModel.class)
                .getResultList();
        return res;
    }
}