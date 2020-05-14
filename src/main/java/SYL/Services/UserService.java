package SYL.Services;


import SYL.Dao.UserDao;
import SYL.Models.PlanModel;
import SYL.Models.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {

    private UserDao usersDao = new UserDao();

    public UserModel getUserByID(int id) {
        return usersDao.getByID(id);
    }

    public void saveUser(UserModel user) {
        usersDao.save(user);
    }

    public void deleteUser(long id) {
        usersDao.delete(id);
    }

    public void updateInfo(UserModel user) {
        usersDao.updateInfo(user);
    }

    public int login(String email, String password) {
        return usersDao.login(email, password);
    }

    public List<UserModel> getAll() {
        return usersDao.getAll();
    }

    public void updatePlan(UserModel user, PlanModel plan){usersDao.updatePlan(user,plan);}

    public UserModel loggedUser(String email) {
        return usersDao.getByEmail(email);
    }
}