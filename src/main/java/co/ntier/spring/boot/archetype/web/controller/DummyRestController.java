package co.ntier.spring.boot.archetype.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyRestController {

	@RequestMapping("/")
	public String index(){
		return "Hello world!";
	}
	
	@Secured("ROLE_ADMIN")
	@RequestMapping("/complex")
	public Map<String, String> complex(){
		return Collections.singletonMap("Hello", "world!");
	}
	
	@RequestMapping("/login")
	public String login(){
		List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
		Authentication token = new UsernamePasswordAuthenticationToken("user", "N/A", auths);
		SecurityContextHolder.getContext().setAuthentication(token);
		return "ok";
	}
	
	@RequestMapping("/whoami")
	public String whoami(Authentication auth){
		return auth == null ? "Not logged in :(" : auth.getName();
	}
	
}
