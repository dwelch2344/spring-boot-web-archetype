package co.ntier.spring.boot.archetype.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContentController {
	
	@RequestMapping("/site")
	public String getSite(Model model){
		return "home";
	}
	
}
