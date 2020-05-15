package SYL.Services;


import SYL.App.BeanConfig;
import SYL.Dao.UserDao;
import SYL.Models.OrderModel;
import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import ru.fix.aggregating.profiler.ProfiledCall;
import ru.fix.aggregating.profiler.Profiler;

import java.util.ArrayList;
import java.util.List;

@Repository
@Import(BeanConfig.class)
public class UserService {

    @Autowired
    private Profiler profiler;

    private final UserDao usersDao = new UserDao();

    public UserModel getUserByID(int id) {
        UserModel user;

        try (ProfiledCall call = profiler.profiledCall("get_user_by_id")) {
            call.start();
            user = usersDao.getByID(id);
            call.stop();
        }

        return user;
    }

    public void saveUser(UserModel user) {
        try (ProfiledCall call = profiler.profiledCall("save_user")) {
            call.start();
            usersDao.save(user);
            call.stop();
        }
    }

    public void deleteUser(long id) {
        try (ProfiledCall call = profiler.profiledCall("delete_user")) {
            call.start();
            usersDao.delete(id);
            call.stop();
        }
    }

    public void updateInfo(UserModel user) {
        try (ProfiledCall call = profiler.profiledCall("update_user_info")) {
            call.start();
            usersDao.updateInfo(user);
            call.stop();
        }
    }

    public int login(String email, String password) {
        int result = -1;

        try (ProfiledCall call = profiler.profiledCall("user_login")) {
            call.start();
            result = usersDao.login(email, password);
            call.stop();
        }

        return result;
    }

    public List<UserModel> getAll() {
        List<UserModel> users = new ArrayList<>();

        try (ProfiledCall call = profiler.profiledCall("get_all_users")) {
            call.start();
            users = usersDao.getAll();
            call.stop();
        }

        return users;
    }

    public void updatePlan(UserModel user, PlanModel plan) {
        try (ProfiledCall call = profiler.profiledCall("update_user_plan")) {
            call.start();
            usersDao.updatePlan(user, plan);
            call.stop();
        }
    }

    public UserModel loggedUser(String email) {
        UserModel user;

        try (ProfiledCall call = profiler.profiledCall("get_user_by_email")) {
            call.start();
            user = usersDao.getByEmail(email);
            call.stop();
        }

        return user;
    }
}