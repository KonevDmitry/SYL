package SYL.Controllers;

import java.util.List;

import SYL.Models.UserModel;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    //ATTENTION!!! Users plans not printed to json, but easily could be got by getters
    // I have no idea how to edit it to json

    @RequestMapping(value = "/users/get_all", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    List<UserModel> getPersons() {
        List<UserModel> users = userService.getAll();
        return users;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public @ResponseBody
    UserModel getPerson(@PathVariable("id") int id) {
        return userService.getUserByID(id);
    }
}