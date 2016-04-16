package com.cmdi.yjs.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cmdi.yjs.util.MD5Util;

@Controller
public class LoginController {

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,@RequestParam("password") String password,
			RedirectAttributes redirectAttributes,Map<String,Object> map) {
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.md5(password));
		token.setRememberMe(true);
		 
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			return "redirect:index.jsp";
		}catch(UnknownAccountException e) {
			redirectAttributes.addFlashAttribute("error", "UnknownAccountException");
			return "redirect:fail.jsp";
		}catch(LockedAccountException e) {
			redirectAttributes.addFlashAttribute("error", "LockedAccountException");
			return "redirect:fail.jsp";
		}catch (AuthenticationException ae ) {  
			redirectAttributes.addFlashAttribute("error", "AuthenticationException");  
			return "redirect:fail.jsp";
		}  
	}
}
