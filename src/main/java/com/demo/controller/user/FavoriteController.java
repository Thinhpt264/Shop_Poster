package com.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entities.Poster;
import com.demo.service.PosterService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({"favorite"})
public class FavoriteController {
	
	@Autowired
	private PosterService posterService;
	
	@GetMapping("index")
	public String index(ModelMap modelMap, HttpSession session) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		List<Poster> favorites = (List<Poster>) session.getAttribute("favorites");
		if(favorites == null) {
			favorites = new ArrayList<>();
		}
		modelMap.put("favorites", favorites);
		return "user/favorite/index";
	}
	
	@PostMapping("add")
	@ResponseBody
	public boolean Add(@RequestParam("productId") int productId, HttpSession httpSession) {
		System.out.println("aa1");
		List<Poster> favoriteList = (List<Poster>) httpSession.getAttribute("favorites");
        if (favoriteList == null) {
            favoriteList = new ArrayList<>();
        }
        
        Poster poster = posterService.findById(productId);
        if(poster != null && !favoriteList.contains(poster)) {
        	favoriteList.add(poster);
        	httpSession.setAttribute("favorites", favoriteList); // Cập nhật danh sách yêu thích vào session
             return true; // Thêm thành công
        }

		
		
        return false; // Sản phẩm đã có trong danh sách yêu thích hoặc không tìm thấy
	}
	
	
	 // Phương thức để xóa sản phẩm khỏi danh sách yêu thích
    @PostMapping("remove")
    @ResponseBody
    public boolean removeFavorite(@RequestParam("productId") int productId, HttpSession session) {
    	System.out.println("aa");
        // Lấy danh sách yêu thích từ session
    	List<Poster> favoriteList = (List<Poster>) session.getAttribute("favorites");
        if (favoriteList != null) {
            // Tìm sản phẩm theo productId
            Poster posterRemove = favoriteList.stream()
                    .filter(product -> product.getId() == productId)
                    .findFirst()
                    .orElse(null);

            // Nếu sản phẩm tồn tại trong danh sách, xóa nó đi
            if (posterRemove != null) {
                favoriteList.remove(posterRemove);
                session.setAttribute("favorites", favoriteList); // Cập nhật danh sách yêu thích vào session
                return true; // Xóa thành công
            }
        }

        return false; // Sản phẩm không tồn tại trong danh sách yêu thích
    }
	
}
