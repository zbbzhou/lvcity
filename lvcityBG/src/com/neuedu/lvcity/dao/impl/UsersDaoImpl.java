package com.neuedu.lvcity.dao.impl;

import java.sql.*;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.UsersDao;
import com.neuedu.lvcity.model.Users;


//ʵ�ֽӿ���
public class UsersDaoImpl implements UsersDao {
	Connection connection = null;
	
	public UsersDaoImpl(Connection  connection ) {
		// TODO Auto-generated constructor stub
		this.connection = connection ;
	}

	@Override
	public Users login(String username, String passwrod) {	
		//��������ֵ����
		Users user = null;
		//����Ԥ����������
		PreparedStatement pStatement = null;
		//�������������
		ResultSet rSet = null;
		
		try {
			//ͨ�����ӽ���Ԥ����������
			pStatement = connection.prepareStatement("select * from users where name=? and passwd=?");
			pStatement.setString(1, username);//1��Ӧ��һ��users��Ĳ���name�ֶ�
			pStatement.setString(2, passwrod);
			
			//ִ��Ԥ����SQL���
			rSet = pStatement.executeQuery();
			
            //�����������ȡ������������ݷ���user����
			if(rSet.next()){
				user = new Users();
				user.setId(rSet.getInt("id"));
				user.setName(rSet.getString("name"));
				user.setPasswd(rSet.getString("passwd"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//�رս������������
			DBUtils.closeStatement(rSet, pStatement);
		}
		//���ز�ѯ�����user����
		return user;
	}

}
