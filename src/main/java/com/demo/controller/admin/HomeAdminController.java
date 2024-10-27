package com.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Order;
import com.demo.service.OrderService;

@Controller
@RequestMapping("admin")
public class HomeAdminController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping({"index"})
	public String index(ModelMap modelMap) {
		List<Order> orders = orderService.finđAll();
		System.out.println(orders);
		modelMap.put("orders", orders);
		return "admin/home";
	}
}
