package com.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entities.Account;
import com.demo.entities.Role;
import com.demo.helper.RandomHelper;
import com.demo.service.AccountService;
import com.demo.service.MailService;
import com.demo.service.RoleServiceJPA;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private RoleServiceJPA roleService;
	
	@GetMapping("index")
	public String index(@RequestParam(value = "error" , required= false) String error, ModelMap modelMap ) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		if(error != null) {
			modelMap.put("msg", "Đăng nhập Thất Bại");
		}
		return "user/login/login";
	}
	
	@PostMapping("process-login")
	public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session
			, RedirectAttributes redirectAttributes) {
		System.out.println(username);
		System.out.println(password);
		if(accountService.login(username, password)) {
			session.setAttribute("username", username);
			return "redirect:/user/home/index";
		}else {
			redirectAttributes.addFlashAttribute("msg", "Sai Tài Khoản Hoặc Mật Khẩu");
			return "redirect:/login/index";
		}
		
	}
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		return "redirect:/login/index";
		
	}
	
	@GetMapping("signup")
	public String signUp(ModelMap modelMap) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		Account account = new Account();
		modelMap.put("account", account);
		return "user/login/register";
	}
	
	@PostMapping("process-signup")
	public String processSignUp(@ModelAttribute("account") Account account, RedirectAttributes redirectAttributes) {
		String password = encoder.encode(account.getPassword());
		account.setPassword(password);
		account.setStatus(false);
		account.setSecurityCode(RandomHelper.random());
		Role role = roleService.findRoleById(1);
		account.setRole(role);
		if(accountService.save(account)) {
			String from = environment.getProperty("spring.mail.username");
			String to = account.getEmail();
			 String verifyLink = environment.getProperty("BASE_URL") + "login/verify?email=" + account.getEmail() + "&securitycode=" + account.getSecurityCode();
			    
			    String content = "<!DOCTYPE html>" +
			                     "<html>" +
			                     "<head>" +
			                     "<style>" +
			                     "body { font-family: Arial, sans-serif; }" +
			                     ".container { max-width: 600px; margin: 0 auto; padding: 20px; }" +
			                     ".btn { background-color: #007bff; color: white; padding: 10px 20px; text-decoration: none; }" +
			                     "</style>" +
			                     "</head>" +
			                     "<body>" +
			                     "<div class='container'>" +
			                     "<h2>Xác minh tài khoản của bạn</h2>" +
			                     "<p>Xin chào,</p>" +
			                     "<p>Vui lòng nhấp vào nút bên dưới để xác minh tài khoản của bạn:</p>" +
			                     "<a href='" + verifyLink + "' class='btn'>Xác minh tài khoản</a>" +
			                     "</div>" +
			                     "</body>" +
			                     "</html>";
			    
			    mailService.send(from, to, "Xác minh tài khoản", content);
			return "redirect:/login/index";
		}else {
			redirectAttributes.addFlashAttribute("msg", "Đăng Ký Không Thành Công");
			return "redirect:/login/signup";
		}
		
		
		
	}
		@GetMapping("verify")
		public String verify(@RequestParam("email") String email, @RequestParam("securitycode") String securityCode, ModelMap modelMap	) {
			Account account = accountService.findByEmail(email);
			System.out.println(account);
			if(account != null) {
				if(account.getSecurityCode().equalsIgnoreCase(securityCode)) {
					account.setStatus(true);
					accountService.save(account);
					modelMap.put("msg", "Thành Công");
					
					return "user/login/verify";
				} else {
					modelMap.put("msg", "Thất Bại");
					return "user/login/verify";
				}
			}else {
				modelMap.put("msg", "Thất Bại");
				return "user/login/verify";
			}
			
		
	}
	
	
}
