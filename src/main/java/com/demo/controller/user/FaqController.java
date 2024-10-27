package com.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entities.Account;
import com.demo.entities.ChatMessage;
import com.demo.entities.Conversation;
import com.demo.service.AccountService;
import com.demo.service.ChatMessageService;
import com.demo.service.ConversationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({"faq"})
public class FaqController {
	
	@Autowired
	private ConversationService conversationService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@GetMapping({"index"})
	public String index(ModelMap modelMap, Authentication authentication) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		if(authentication != null) {
			Account account = accountService.findByUserName(authentication.getName());
			Conversation conversation = conversationService.findLastUser(account);
			if(conversation != null) {
				List<ChatMessage> messages = chatMessageService.findAllByConversationId(conversation.getId());
				modelMap.put("conversation_id", conversation.getId());
				modelMap.put("messages", messages);
			}else {
				modelMap.put("conversation_id", 0);
				
			}
			
		}
		
		return "user/faqs/faqs";
	}
	
	@PostMapping("process-chat")
	public String processChat(ModelMap modelMap,Authentication authentication, @RequestParam("message") String message, @RequestParam("role") int role, @RequestParam("conversation_id") int conversation_id)  {
		ChatMessage chatMessage = new ChatMessage();
		System.out.println(authentication.getName());
		chatMessage.setMessage(message);
		chatMessage.setRole(role);
		chatMessage.setSender(accountService.findByUserName(authentication.getName()));
		if(conversation_id == 0) {
			Conversation conversation = new Conversation();
			Account account = accountService.findByUserName(authentication.getName());
			conversation.setUser_id(account);
			if(conversationService.save(conversation)) {
				chatMessage.setConversation(conversationService.findById(conversation.getId()));
			}
		}else {
			chatMessage.setConversation(conversationService.findById(conversation_id));
		}
		
		if(chatMessageService.save(chatMessage)) {
			return "redirect:/faq/index";
		}
		return "redirect:/faq/index";
		
	}
}
