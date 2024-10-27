package com.demo.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.entities.ChatMessage;
import com.demo.entities.Conversation;
import com.demo.service.AccountService;
import com.demo.service.ChatMessageService;
import com.demo.service.ConversationService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("admin/chat")
public class ChatController {
	
	@Autowired
	private ConversationService conversationService;
	
	@Autowired
	private ChatMessageService chatMessageService;
	
	@Autowired
	private AccountService accountService;
	
	
	@GetMapping("index")
	public String index(ModelMap modelMap, HttpSession session) {
		List<Conversation> conversations = conversationService.findAll();
		modelMap.put("conversations", conversations);
		List<ChatMessage> chatMessages;
		if(session.getAttribute("conversation_id") == null) {
			modelMap.put("conversation_id", conversations.get(0).getId());
			chatMessages = chatMessageService.findAllByConversationId(conversations.get(0).getId());
			
		}else {
			modelMap.put("conversation_id", (int) session.getAttribute("conversation_id"));
			chatMessages = chatMessageService.findAllByConversationId((int) session.getAttribute("conversation_id"));
		}
		
		modelMap.put("chatMessages", chatMessages);
		return "admin/chat";
	}
	
	@PostMapping("process-chat")
	public String processChat(ModelMap modelMap, HttpSession session,Authentication authentication, @RequestParam("message") String message, @RequestParam("role") int role, @RequestParam("conversation_id") int conversation_id)  {
		ChatMessage chatMessage = new ChatMessage();
		List<Conversation> conversations = conversationService.findAll();
		modelMap.put("conversations", conversations);
		System.out.println(authentication.getName());
		
		List<ChatMessage> chatMessages = chatMessageService.findAllByConversationId(conversations.get(0).getId());
		if(session.getAttribute("conversation_id") == null) {
			modelMap.put("conversation_id", conversations.get(0).getId());
		}
		chatMessage.setMessage(message);
		chatMessage.setRole(role);
		chatMessage.setConversation(conversationService.findById(conversation_id));
		chatMessage.setSender(accountService.findByUserName(authentication.getName()));
		if(chatMessageService.save(chatMessage)) {
			modelMap.put("chatMessages", chatMessages);
			return "redirect:/admin/chat/index";
		}
		
		return "admin/chat";
	}
	
	@GetMapping("message/{index}")
	public String changeChat(ModelMap modelMap, @PathVariable("index") int index, HttpSession httpSession) {
		Conversation conversation = conversationService.findById(index);
		httpSession.setAttribute("conversation_id", conversation.getId());
		
		return "redirect:/admin/chat/index";		
				
	}
}
