package SYL.Controllers;

import java.util.List;

import SYL.Models.UserModel;
import SYL.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/get_all", method = RequestMethod.GET)
    public String getPersons(Model model) {

        List<UserModel> users = userService.getAll();
        model.addAttribute("users", users);

        // This will resolve to /WEB-INF/jsp/personspage.jsp

        //энес, ну тут это, фронт должен быть, но чё-то впадлу :D

        return "personspage";
    }

}