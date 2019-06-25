package com.fx.spring4all.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fx.spring4all.entity.UserDO;

@Repository
public interface UserRepository extends CrudRepository<UserDO, Long>  {

	UserDO findByUsername(String username);
	
}
