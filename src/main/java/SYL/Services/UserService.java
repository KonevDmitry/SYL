package SYL.Services;


import SYL.Dao.UserDao;
import SYL.Models.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserService {

    private UserDao usersDao = new UserDao();

    public UserModel findUser(int id) {
        return usersDao.findById(id);
    }

    public void saveUser(UserModel user) {
        usersDao.save(user);
    }

    public void deleteUser(UserModel user) {
        usersDao.delete(user);
    }

    public void updateUser(UserModel user) {
        usersDao.update(user);
    }

    public List<UserModel> getAll() {return usersDao.getAll();}
}