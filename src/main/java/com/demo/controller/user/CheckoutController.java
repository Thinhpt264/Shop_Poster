package com.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entities.Item;
import com.demo.entities.Order;
import com.demo.helper.CartHelper;
import com.demo.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("checkout")
public class CheckoutController {
	
	@Autowired
	private OrderService orderService;
	
	
	@GetMapping({"index", ""})
	public String index(ModelMap modelMap, HttpSession session) {
		 Boolean checkoutAllowed = (Boolean) session.getAttribute("checkout_allowed");
		 Order order = new Order();
		 
		 if (checkoutAllowed == null || !checkoutAllowed) {
		        return "redirect:/cart/index"; // Redirect về trang giỏ hàng nếu truy cập không hợp lệ
		}else {
			if(session.getAttribute("cart") != null) {
				List<Item> cart = (List<Item>) session.getAttribute("cart");
				if(cart != null) {
					int total = (int) CartHelper.total(cart);
					
					modelMap.put("cart", cart);
					modelMap.put("total", total);
					modelMap.put("order", order);
					
				}else {
					 session.removeAttribute("checkout_allowed"); // Xóa flag sau khi thanh toán
					return "redirect:/products/index";
				}
				session.removeAttribute("checkout_allowed"); // Xóa flag sau khi thanh toán
				return "user/checkout/index";
			}else {
				session.removeAttribute("checkout_allowed"); // Xóa flag sau khi thanh toán
				return "redirect:/products/index";
			}
		}	
	}
	@PostMapping("payment")
	public String paymentInfo(@ModelAttribute("order") Order o, HttpSession session, ModelMap modelMap ) {
		System.out.println(o);
		if(orderService.save(o)) {
			int orderId = o.getId();
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int total = (int) CartHelper.total(cart);
			modelMap.put("orderId", orderId);
			modelMap.put("total", total);
			return "user/checkout/repayment";
		}else {
			return "redirect:/products/index";
		}
		
	}
	@GetMapping({"checkoutsuccess"})
	public String checkoutSuccess(HttpSession httpSession) {
		httpSession.removeAttribute("cart");
		return "user/checkout/checkoutsuccess";
	}
}
