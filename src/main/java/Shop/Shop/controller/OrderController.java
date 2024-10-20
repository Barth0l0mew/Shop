package Shop.Shop.controller;

import Shop.Shop.model.*;
import Shop.Shop.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    MyOrderService myOrderService;
    @GetMapping("/order/orderlist")
    public String productList(Model model) {
        model.addAttribute("page", "orderlist");
        System.out.println(myOrderService.getMyOrders());
        model.addAttribute("orders", myOrderService.getMyOrders());
        return "admin/index";
    }
    @PostMapping("/order/orderedit")
    public String editproduct(@RequestParam(name = "id") Long id, Model model) {
        System.out.println(id);
        Order order = myOrderService.findById(id);
        System.out.println("======orderEdit======= " + order.toString());
        model.addAttribute("orderedit", order);
        model.addAttribute("status", Status.values());
        model.addAttribute("page", "orderedit");
        return "admin/index"; // имя вашего HTML-шаблона
    }
    @PostMapping("/order/saveorder")
    public String saveorder(@ModelAttribute("orderedit")  Order order, Model model) {
        System.out.println("save order====   "+order.toString());
        myOrderService.saveOrder(order);
        return "redirect:/admin/order/orderlist";
    }
}
