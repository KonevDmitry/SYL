package SYL.Controllers;

import SYL.Models.OrderModel;
import SYL.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders/getAll", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPersons() {
        return new ModelAndView("AllOrders").addObject("orders", orderService.getAll());
    }

    @RequestMapping(value = "/order/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") long id) {
        return new ModelAndView("OrderInfo").addObject("order", orderService.getOrderByID(id));
    }

    @RequestMapping(value = "/order/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePerson(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return new ModelAndView("redirect:/orders/getAll");
    }

    @RequestMapping(value = "/orders/getUserOrders/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPersons(@PathVariable("id") long id) {
        return new ModelAndView("AllOrders").addObject("orders", orderService.getUserOrders(id));
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        return new ModelAndView("order").addObject("order", new OrderModel());
    }

    @RequestMapping(value = "/orderProcess", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("order") OrderModel order) {
        orderService.saveOrder(order);
        return new ModelAndView("AllOrders").addObject("orders", orderService.getAll());
    }
}
