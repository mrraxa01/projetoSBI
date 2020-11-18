package com.mrraxa01.projetoSBI.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mrraxa01.projetoSBI.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
	
	try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	catch (Exception e) {
		return null;
	}
	}

}
