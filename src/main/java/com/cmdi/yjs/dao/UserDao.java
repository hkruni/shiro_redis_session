package com.cmdi.yjs.dao;

import org.apache.ibatis.annotations.Param;

import com.cmdi.yjs.model.User;

public interface UserDao {

	public User getUser(@Param(value = "username") String username);
}
