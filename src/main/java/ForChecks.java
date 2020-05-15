import SYL.Dao.OrderDao;
import SYL.Dao.PlanDao;
import SYL.Dao.RegisterDao;
import SYL.Dao.UserDao;
import SYL.Models.OrderModel;
import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import SYL.Services.PlanService;
import SYL.Services.UserService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ForChecks {

    public static void login() {
        UserDao users = new UserDao();
        System.out.println(users.login("email1", "passw"));
        System.out.println(users.login("email1", "passwsdsad"));
        System.out.println(users.login("sacascascasc", "passw"));
    }

    public static void register() {
        UserDao users = new UserDao();
        UserModel user = new UserModel();
        user.setEmail("scacas");
        user.setName("sacasca");
        user.setSurname("ascasca");
        user.setPassword("cascasca");
        users.save(user);
    }

    public static void getAll() {
        UserDao users = new UserDao();
        System.out.println(users.getAll());
    }

    public static void getbyID(long id) {
        UserDao users = new UserDao();
        System.out.println(users.getByID(id));
    }

    public static void updatePlan(long pid) {
        UserDao users = new UserDao();
        PlanDao plans = new PlanDao();
        UserModel user = users.getByID(1);
        System.out.println(user.getPlan().getId());
        users.updatePlan(user, plans.getByID(pid));
        System.out.println(user.getPlan());
    }

    public static void delete(long id) {
        UserDao users = new UserDao();
        users.delete(id);
        System.out.println(users.getAll().size());
    }

    public static void planByID(long id) {
        PlanDao plans = new PlanDao();
        System.out.println(plans.getByID(id));
    }

    public static void allPlans() {
        PlanDao plans = new PlanDao();
        System.out.println(plans.getAll());
    }

    public static void addPrivelege(long pid, String privelege) {
        PlanDao plans = new PlanDao();
        PlanModel plan = plans.getByID(pid);
        plans.addPrivelege(plan, privelege);
    }

    public static void deletePrivelege(long pid, String privelege) {
        PlanDao plans = new PlanDao();
        PlanModel plan = plans.getByID(pid);
        plans.deletePrivelege(plan, privelege);
    }

    public static void deletePlan(long pid) {
        PlanDao plans = new PlanDao();
        plans.delete(pid);
        System.out.println(plans.getAll().size());
    }

    public static void addPlan() {
        PlanDao plans = new PlanDao();
        PlanModel plan = new PlanModel();
        plan.setDesc("scascsa");
        plan.setCost(1231);
        List<String> privs = new ArrayList<>();
        privs.add("sXAS");
        privs.add("casacs");
        plan.setPriveleges(privs);
        plans.save(plan);
    }

    public static void allOrders() {
        OrderDao orders = new OrderDao();
        System.out.println(orders.getAll());
    }

    public static void orderByID(long id) {
        OrderDao orders = new OrderDao();
        System.out.println(orders.getByID(id));
    }

    public static void deleteOrder(long id) {
        OrderDao orders = new OrderDao();
        orders.delete(id);
        System.out.println(orders.getAll().size());
    }

    public static void userOrders(long id){
        OrderDao orders = new OrderDao();
        System.out.println(orders.getUserOrders(id));
    }

    public static void addOrder(){
        OrderDao orders = new OrderDao();
        OrderModel order = new OrderModel();
        order.setUserID(5);
        order.setPrivelege("casca");
        orders.save(order);
    }
    public static void main(String[] args) throws SQLException {
        //login();
        //register();
        //getAll();
        //getbyID(1);
        //delete(13);
        //updatePlan(3);

        //planByID(1);
        //allPlans();
        //addPrivelege(3,"test");
        //deletePrivelege(3,"test");
        //deletePlan(4);
        //addPlan();

//        allOrders();
//        orderByID(1);
//        deleteOrder(3);
//        userOrders(3);
//        addOrder();
    }
}