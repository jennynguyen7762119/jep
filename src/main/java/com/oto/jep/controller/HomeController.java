/**
 * 
 */
package com.oto.jep.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author thuyntp
 *
 */
@Controller
public class HomeController {

	public static final String HOME_ACTION = "/home";
	public static final String HOME_VIEW = "index";
	
	@RequestMapping(value = HOME_ACTION, method = RequestMethod.GET)
	public String goToHomePage(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("test");
		return HOME_VIEW;
	}
}
