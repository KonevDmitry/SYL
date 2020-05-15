package SYL.Dao;

import SYL.Models.OrderModel;
import SYL.Utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderDao {
    public void save(OrderModel order) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(order);
        tx1.commit();
        session.close();
    }

    public OrderModel getByID(long id) {
        return (OrderModel) HibernateSessionFactoryUtil.getSessionFactory().openSession().get(OrderModel.class, id);
    }

    public void delete(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        OrderModel order = getByID(id);
        session.delete(order);
        tx1.commit();
        session.close();

    }

    public List<OrderModel> getAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<OrderModel> res = session.createNativeQuery("SELECT " +
                " o.id,userid,privelege" +
                " FROM sylschema.orders o", OrderModel.class)
                .getResultList();
        return res;
    }

    public List<OrderModel> getUserOrders(long userID) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<OrderModel> res = session.createNativeQuery("SELECT " +
                " o.id,userid,privelege" +
                " FROM sylschema.orders o WHERE userid=?1", OrderModel.class)
                .setParameter(1, userID)
                .getResultList();
        return res;
    }
}
