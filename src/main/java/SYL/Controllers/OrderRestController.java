package SYL.Controllers;

import SYL.Models.OrderModel;
import SYL.Services.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class OrderRestController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/orders/getAll", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPersons() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orderService.getAll());
        mv.setViewName("AllOrders");
        return mv;
    }

    @RequestMapping(value = "/order/get/{id}", method = RequestMethod.GET)
    public ModelAndView getPerson(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("order", orderService.getOrderByID(id));
        mv.setViewName("OrderInfo");
        return mv;
    }

    @RequestMapping(value = "/order/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ModelAndView deletePerson(@PathVariable("id") long id) {
        orderService.deleteOrder(id);
        return new ModelAndView("redirect:/orders/getAll");
    }

    @RequestMapping(value = "/orders/getUserOrders/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ModelAndView getPersons(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orderService.getUserOrders(id));
        mv.setViewName("AllOrders");
        return mv;
    }

    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("order");
        mav.addObject("order", new OrderModel());
        return mav;
    }

    @RequestMapping(value = "/orderProcess", method = RequestMethod.POST)
    public ModelAndView addUser(@ModelAttribute("order") OrderModel order) {
        orderService.saveOrder(order);
        ModelAndView mv = new ModelAndView();
        mv.addObject("orders", orderService.getAll());
        mv.setViewName("AllOrders");
        return mv;
    }
}
