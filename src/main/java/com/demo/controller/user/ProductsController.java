package com.demo.controller.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.entities.Frame;
import com.demo.entities.Item;
import com.demo.entities.Keyword;
import com.demo.entities.Poster;
import com.demo.entities.PosterKeyword;
import com.demo.entities.Size;
import com.demo.helper.CartHelper;
import com.demo.repositories.PosterRepository;
import com.demo.service.FrameService;
import com.demo.service.KeywordService;
import com.demo.service.PosterKeywordService;
import com.demo.service.PosterService;
import com.demo.service.SizeService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping({ "products" })
public class ProductsController {

	@Autowired
	private PosterService posterService;
	@Autowired
	private FrameService frameService;
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private KeywordService keywordService;
	
	@Autowired
	private PosterKeywordService posterKeywordService;

	@GetMapping({ "index" })
	public String index(ModelMap modelMap, HttpSession httpSession) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		List<Poster> posters = posterService.findAll();
		List<Size> sizes = sizeService.findAll();
		List<Frame> frames = frameService.findAll();
		List<Poster> favorites = (List<Poster>) httpSession.getAttribute("favorites");
		if(favorites == null) {
			favorites = new ArrayList<>();
		}
		System.out.println(favorites);
		modelMap.put("favorites", favorites);
		modelMap.put("frames", frames);
		modelMap.put("sizes", sizes);
		modelMap.put("posters", posters);
		return "user/products/products";
	}

	@GetMapping({ "detail/{id}" })
	public String details(ModelMap modelMap, @PathVariable("id") int id) {
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		modelMap.put("currentUri", currentUri);
		Poster poster = posterService.findById(id);
		System.out.println(poster);
		modelMap.put("poster", poster);
		return "user/products/productDetail";
	}

	@GetMapping(value = "findBySizeId", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Poster> findPosterBySizeId(@RequestParam("size_id") int id) {
		if (id == -1) {
			return posterService.findAll();
		}
		return posterService.findPosterBySize(id);
	}

	@GetMapping(value = "findByFrameId", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Poster> findPosterByFrameId(@RequestParam("frame_id") int id) {
		return posterService.findPosterByFrame(id);
	}

	@PostMapping({ "addToCart" })
	public String addToCart(@RequestParam("poster_id") int poster_id, @RequestParam("frame_id") int frame_id,
			@RequestParam("size_id") int size_id, HttpSession httpSession, ModelMap modelMap) {
		Poster poster = posterService.findById(poster_id);
		String currentUri = ServletUriComponentsBuilder.fromCurrentRequest().toUriString();
		Size size = sizeService.findById(size_id);
		Frame frame = frameService.findById(frame_id);
		modelMap.put("currentUri", currentUri);
		if(httpSession.getAttribute("cart") == null) {
			List<Item> cart = new ArrayList<Item>();
			cart.add(new Item(poster, size, frame, 1));
			httpSession.setAttribute("cart", cart);
		}else {
			List<Item> cart = (List<Item>) httpSession.getAttribute("cart");
			int index = CartHelper.findIndexCart(cart, poster_id, size_id, frame_id);
			if(index == -1) {
				cart.add(new Item(poster, size, frame, 1));
			}else {
				int quantity = cart.get(index).getQuantity() + 1;
				cart.get(index).setQuantity(quantity);
			}
			httpSession.setAttribute("cart", cart);
		}
		return "redirect:/cart/index";
	}
	
	@PostMapping("searchByKeyword")
	@ResponseBody
	public List<Poster> findByKeyword(@RequestParam("keyword")String keyword) {
		List<Poster> result = new ArrayList<>();
		String[] words = keyword.split(" ");
		List<Keyword> keywords = new ArrayList<>();
		List<PosterKeyword> AllPoster = new ArrayList<>();
	    Set<Integer> posterIds = new HashSet<>();
		for (String word : words) {
			Keyword keyword1 = keywordService.findByName(word);
			if(keyword1 != null) {
			keywords.add(keyword1);
			}
		
		}
		  // Lấy tất cả PosterKeyword tương ứng với từng Keyword
	    for (Keyword keywordEntity : keywords) {
	        List<PosterKeyword> posterKeywords = posterKeywordService.getListKeywords(keywordEntity.getId());
	        AllPoster.addAll(posterKeywords);
	    }
	    for (PosterKeyword pk : AllPoster) {
            posterIds.add(pk.getPoster().getId());  // Thêm ID của Poster vào tập hợp
        }
	    
	   for(Integer i: posterIds) {
		   Poster poster = posterService.findById(i);
		   if(poster != null) {
			   result.add(poster);
		   }
	   }
	    System.out.println(result);
		return result;
	}

}
