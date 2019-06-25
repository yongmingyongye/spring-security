package com.fx.spring4all.service;

import com.fx.spring4all.entity.UserDO;

public interface UserService {
	
	/**
	 * 新增用户
	 * userDO.username 必须唯一，默认USER权限
	 * @param userDO
	 */
	void insert(UserDO userDO);
	
	/**
	 * 通过用户名查询用户信息
	 * @param username 用户名（账号名）
	 * @return UserDO对象
	 */
	UserDO getByUsername(String username);

}
