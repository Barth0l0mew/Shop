package Shop.Shop.controller;

import Shop.Shop.model.Order;
import Shop.Shop.model.Product;
import Shop.Shop.service.MyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("page", "orderedit");
        return "admin/index"; // имя вашего HTML-шаблона
    }
}
