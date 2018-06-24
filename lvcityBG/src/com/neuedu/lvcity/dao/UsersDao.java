package com.neuedu.lvcity.dao;

import com.neuedu.lvcity.model.Users;
//定义接口
public interface UsersDao {
	public Users login(String username,String password);
	
}
