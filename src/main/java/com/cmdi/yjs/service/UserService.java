package com.cmdi.yjs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmdi.yjs.dao.UserDao;
import com.cmdi.yjs.model.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User getUser(String username) {
		User user = userDao.getUser(username);
		return user;
		
	}
}
