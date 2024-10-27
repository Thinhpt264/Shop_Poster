package com.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/products/poster")
public class ProductPosterAdminController {
	@GetMapping("")	
	public String index() {
	return "admin/products";
	}
}
