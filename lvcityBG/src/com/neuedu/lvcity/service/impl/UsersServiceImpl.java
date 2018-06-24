package com.neuedu.lvcity.service.impl;

import java.sql.Connection;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UsersDao;
import com.neuedu.lvcity.dao.impl.UsersDaoImpl;
import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;

public class UsersServiceImpl implements UsersService{
	
	/**
	 * ���췽��˽�л�
	 */
	private UsersServiceImpl(){
		// TODO Auto-generated method stub
	}
	
	/**
	 * (����ģʽ)����һ��Ψһ��ʵ��
	 */
	private static UsersService Instance=new UsersServiceImpl();
	
	//���ṩһ������Ĺ����ӿڷ��������ʣ�
	public static UsersService getInStance(){
		return Instance;  //����һ������ʵ��
	}
	
	@Override
	public Users login(String username, String password) {
		// TODO Auto-generated method stub
		Users users=null;//����һ�����󣬱��淵�ؽ��
		Connection conn=null;//����һ�����ӣ���Ϊ��
		try{
			
		conn=DBUtils.getConnection();//��ȡ����
		UsersDao usersDao=new UsersDaoImpl(conn);//�õ�dao��ʵ����Ķ���
		users=usersDao.login(username, password);//����dao��
		}catch(Exception e){
			System.out.println("��¼��ѯ�û�ʱ�����쳣"+e.getMessage());
		}finally{
			DBUtils.closeConnection(conn);
		}
		return users;
	}

}
