package com.neuedu.lvcity.service.impl;

import java.sql.Connection;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UsersDao;
import com.neuedu.lvcity.dao.impl.UsersDaoImpl;
import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;

public class UsersServiceImpl implements UsersService{
	
	/**
	 * 构造方法私有化
	 */
	private UsersServiceImpl(){
		// TODO Auto-generated method stub
	}
	
	/**
	 * (单例模式)创建一个唯一的实例
	 */
	private static UsersService Instance=new UsersServiceImpl();
	
	//（提供一个对外的公共接口方法来访问）
	public static UsersService getInStance(){
		return Instance;  //返回一个公共实例
	}
	
	@Override
	public Users login(String username, String password) {
		// TODO Auto-generated method stub
		Users users=null;//声明一个对象，保存返回结果
		Connection conn=null;//声明一个连接，先为空
		try{
			
		conn=DBUtils.getConnection();//获取连接
		UsersDao usersDao=new UsersDaoImpl(conn);//拿到dao层实现类的对象
		users=usersDao.login(username, password);//调用dao层
		}catch(Exception e){
			System.out.println("登录查询用户时出现异常"+e.getMessage());
		}finally{
			DBUtils.closeConnection(conn);
		}
		return users;
	}

}
