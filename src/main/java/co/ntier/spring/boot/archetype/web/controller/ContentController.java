package co.ntier.spring.boot.archetype.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.actuate.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;

import co.ntier.spring.boot.archetype.web.svc.ExampleBadService;

@Slf4j
@Controller
public class ContentController implements ErrorController {
	
	private static final String ERROR_KEY = "error";
	
	@Inject
	private ExampleBadService service;
	
	@RequestMapping("/site")
	public String getSite(Model model){
		model.addAttribute("people", service.getPeople());
		return "home";
	}
	
	@RequestMapping("/error")
	public String getError(Model model){
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@Override
	public Map<String, Object> extract(RequestAttributes attributes, boolean trace) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("timestamp", new Date());
		try {
			Throwable error = (Throwable) attributes.getAttribute(
					"javax.servlet.error.exception", RequestAttributes.SCOPE_REQUEST);
			Object obj = attributes.getAttribute("javax.servlet.error.status_code",
					RequestAttributes.SCOPE_REQUEST);
			int status = 999;
			if (obj != null) {
				status = (Integer) obj;
				map.put(ERROR_KEY, HttpStatus.valueOf(status).getReasonPhrase());
			}
			else {
				map.put(ERROR_KEY, "None");
			}
			map.put("status", status);
			if (error != null) {
				while (error instanceof ServletException && error.getCause() != null) {
					error = ((ServletException) error).getCause();
				}
				map.put("exception", error.getClass().getName());
				map.put("message", error.getMessage());
				if (trace) {
					StringWriter stackTrace = new StringWriter();
					error.printStackTrace(new PrintWriter(stackTrace));
					stackTrace.flush();
					map.put("trace", stackTrace.toString());
				}
				log.error("Failed..", error);
			}
			else {
				Object message = attributes.getAttribute("javax.servlet.error.message",
						RequestAttributes.SCOPE_REQUEST);
				map.put("message", message == null ? "No message available" : message);
			}
			return map;
		}
		catch (Exception ex) {
			map.put(ERROR_KEY, ex.getClass().getName());
			map.put("message", ex.getMessage());
			log.error("Something really went wrong", ex);
			return map;
		}
	}
	
}
