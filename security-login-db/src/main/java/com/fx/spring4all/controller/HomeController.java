package com.fx.spring4all.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fx.spring4all.entity.UserDO;
import com.fx.spring4all.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

	private final UserService userService;
	
	@GetMapping({"/", "/index", "/home"})
	public String root() {
		return "index";
	}
	
	@GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(UserDO userDO){
    	if(userDO == null)
    		return "redirect:register?error";
    	if(userDO.getUsername() == null || "".equals(userDO.getUsername())) {
    		return "redirect:register?error";
    	}
    	if(userDO.getPassword() == null || "".equals(userDO.getPassword())) {
    		return "redirect:register?error";
    	}
    	if(userDO.getNickname() == null || "".equals(userDO.getNickname())) {
    		return "redirect:register?error";
    	}
    	UserDO usertemp = userService.getByUsername(userDO.getUsername());
    	if(usertemp != null) {
    		return "redirect:register?error";
    	}
        userService.insert(userDO);
        return "redirect:register?success";
    }
	
}
