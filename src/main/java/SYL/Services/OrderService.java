package SYL.Services;

import SYL.Dao.OrderDao;
import SYL.Models.OrderModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public OrderModel getOrderByID(long ID){
        return orderDao.getByID(ID);
    }

    public void saveOrder(OrderModel order){
        orderDao.save(order);
    }

    public void deleteOrder(long id){
        orderDao.delete(id);
    }

    public List<OrderModel> getUserOrders(long id){
        return orderDao.getUserOrders(id);
    }

    public List<OrderModel> getAll(){
        return orderDao.getAll();
    }
}
