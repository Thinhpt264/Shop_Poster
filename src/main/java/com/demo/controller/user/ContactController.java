package com.demo.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping({"contact"})
public class ContactController {
	
	@GetMapping({"index"})
	public String index(ModelMap modelMap) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		return "user/contact/contact";
	}
}
