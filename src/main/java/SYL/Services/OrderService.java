package SYL.Services;

import SYL.App.BeanConfig;
import SYL.Dao.OrderDao;
import SYL.Models.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import ru.fix.aggregating.profiler.ProfiledCall;
import ru.fix.aggregating.profiler.Profiler;

import java.util.ArrayList;
import java.util.List;

@Repository
@Import(BeanConfig.class)
public class OrderService {

    @Autowired
    private Profiler profiler;

    private final OrderDao orderDao = new OrderDao();

    public OrderModel getOrderByID(long ID) {
        OrderModel order;

        try (ProfiledCall call = profiler.profiledCall("get_order_by_id")) {
            call.start();
            order = orderDao.getByID(ID);
            call.stop();
        }

        return order;
    }

    public void saveOrder(OrderModel order) {
        try (ProfiledCall call = profiler.profiledCall("save_order")) {
            call.start();
            orderDao.save(order);
            call.stop();
        }
    }

    public void deleteOrder(long id) {
        try (ProfiledCall call = profiler.profiledCall("delete_order")) {
            call.start();
            orderDao.delete(id);
            call.stop();
        }
    }

    public List<OrderModel> getUserOrders(long id) {
        List<OrderModel> orders;

        try (ProfiledCall call = profiler.profiledCall("get_user_orders")) {
            call.start();
            orders = orderDao.getUserOrders(id);
            call.stop();
        }

        return orders;
    }

    public List<OrderModel> getAll() {
        List<OrderModel> orders;

        try (ProfiledCall call = profiler.profiledCall("get_all_orders")) {
            call.start();
            orders = orderDao.getAll();
            call.stop();
        }

        return orders;
    }
}
