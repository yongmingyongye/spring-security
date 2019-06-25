package com.fx.spring4all.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fx.spring4all.entity.UserDO;
import com.fx.spring4all.repository.UserRepository;
import com.fx.spring4all.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Primary
@Slf4j
public class BaseUserService implements UserService {
	
	private final UserRepository userRepository;
	
	public BaseUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void insert(UserDO userDO) {
		log.info("保存用户");
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String username = userDO.getUsername();
		if(exist(username)) {
			throw new RuntimeException("用户名已存在！");
		}
		userDO.setPassword(passwordEncoder.encode(userDO.getPassword()));
		userRepository.save(userDO);
	}

	@Override
	public UserDO getByUsername(String username) {
		log.info("通过用户名查询用户");
		return userRepository.findByUsername(username);
	}
	
	/**
	 * 通过用户名判断用户是否存在
	 * @param username
	 * @return
	 */
	private boolean exist(String username) {
		UserDO userDO = userRepository.findByUsername(username);
		return (userDO != null);
	}

}
