package com.fx.spring4all.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fx.spring4all.entity.UserDO;
import com.fx.spring4all.service.UserService;

@Service
public class DbUserDetailsService implements UserDetailsService {

	private final UserService userService;
	
	@Autowired
	public DbUserDetailsService(UserService userService) {
		this.userService = userService;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDO userDO = userService.getByUsername(username);
		if(userDO == null) {
			throw new UsernameNotFoundException("用户不存在");
		}
		List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
		simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));//写死，默认USER权限
		return new User(userDO.getUsername(), userDO.getPassword(), simpleGrantedAuthorities);
	}

}
