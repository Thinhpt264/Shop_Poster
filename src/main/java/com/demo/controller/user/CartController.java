package com.demo.controller.user;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entities.Item;
import com.demo.helper.CartHelper;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({"cart"})
public class CartController {
	@GetMapping({"index"})
	public String index(ModelMap modelMap, HttpSession  session) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		
		if(session.getAttribute("cart") != null) {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			int total = (int) CartHelper.total(cart);
			modelMap.put("cart", cart);
			modelMap.put("total", total);
		}
	
		return "user/cart/index";
	}
	
	@GetMapping({"remove/{index}"})
	public String remove(@PathVariable("index") int index,HttpSession  session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		cart.remove(index);
		session.setAttribute("cart", cart);
		return "redirect:/cart/index";
	}
	
	@GetMapping({"minus/{index}"})
	public String minus(@PathVariable("index") int index,HttpSession  session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		if(cart.get(index).getQuantity() == 1) {
			cart.remove(index);
			session.setAttribute("cart", cart);
		}else {
			int quantity = cart.get(index).getQuantity() - 1;
			cart.get(index).setQuantity(quantity);
			session.setAttribute("cart", cart);
		}
		
	
		return "redirect:/cart/index";
	}
	
	@PostMapping({"processToCheckout"})
	@ResponseBody
	public boolean processToCheckout(HttpSession session) {
		boolean result = true;
		 session.setAttribute("checkout_allowed", true);
		return result;
	}
	
	@GetMapping({"plus/{index}"})
	public String plus(@PathVariable("index") int index,HttpSession  session) {
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		int quantity = cart.get(index).getQuantity() + 1;
		cart.get(index).setQuantity(quantity);
		session.setAttribute("cart", cart);
	
		return "redirect:/cart/index";
	}
}
