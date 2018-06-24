package com.neuedu.lvcity.dao.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.BanarDao;
import com.neuedu.lvcity.model.Banar;



public class BanarDaoImpl implements BanarDao {
		
		/**
		 * ���ݿ�����
		 */
		private Connection conn;
	
		/**
		 * ���췽��
		 * 
		 * @param conn
		 *            ���ݿ�����
		 */
		public BanarDaoImpl(Connection conn) {
			this.conn = conn;
		}

	@Override
	public int banarCount() {
		//�������������ڱ����ѯ���
		int result = 0;
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;
		//���������������������ڱ������ݿ��ѯ���
		ResultSet rs = null;
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("select count(*) from banar");
			//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
			rs = pstam.executeQuery();
			
			//�����ѯ�����Ϊ�գ���ȡ�������ͳ�Ƶ�����
			if(rs.next()){
				result = rs.getInt("count(*)");
							}	
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("�ڲ�ѯͳ��banar��ʱ�������.������Ϣ�� ��" + e.getMessage());			
		} finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(rs, pstam);
		}
	    //���ز�ѯ�����û��б�
		return result;
	}

	@Override
	public List<Banar> findAllBanar(Map<String, Object> map) {
		// TODO Auto-generated method stub
				//�������������ڱ����ѯ���
				List<Banar> list = new ArrayList<Banar>();
				//����Ԥ���������������������ڽ������ݿ����������
				PreparedStatement pstam = null;
				//���������������������ڱ������ݿ��ѯ���
				ResultSet rs = null;
				int startPage = (int) map.get("startPage");
				int endPage = (int) map.get("endPage");
				
				try {
					//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
					pstam = conn.prepareStatement("select * from banar limit ?,?");
					//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
					pstam.setInt(1, startPage);
					pstam.setInt(2, endPage);
					rs = pstam.executeQuery();
					//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
					while(rs.next()){
					//�����û��������ڷ�װ��ǰ�α��еĸ����ֶε�ֵ
					Banar banar = new Banar();
					banar.setBanarid(rs.getInt("banarid"));
					banar.setImage(rs.getString("image"));
					banar.setState(rs.getInt("state"));
								
					//���������������������ڱ������ݿ��ѯ���
					list.add(banar);
					}	
				}catch (SQLException e) {
					//��������쳣������쳣��Ϣ
					System.out.println("�ڲ�ѯȫ��banar��ʱ�������.������Ϣ�� ��" + e.getMessage());			
				} finally {
					//�������ݿ⹤���࣬�رս�����������������
					DBUtils.closeStatement(rs, pstam);
				}
			    //���ز�ѯ�����û��б�
				return list;
	}

	@Override
	public int addBanar(String imagePath) {
		//�������������ڱ����ѯ���
		int result = 0;
		//����Ԥ���������������������ڽ������ݿ����������
		PreparedStatement pstam = null;				
		try {
			//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
			pstam = conn.prepareStatement("insert into banar(image) values(?)");
			pstam.setString(1, imagePath);
			
			//����Ԥ��������executeQuery������ִ�в�ѯ���������ز�ѯ�������ֵ��������������
			result = pstam.executeUpdate();
			//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
				
		}catch (SQLException e) {
			//��������쳣������쳣��Ϣ
			System.out.println("����banar����.������Ϣ�� ��" + e.getMessage());			
		} finally {
			//�������ݿ⹤���࣬�رս�����������������
			DBUtils.closeStatement(null, pstam);
		}
	    //���ز�ѯ�����û��б�
		return result;
	}

	@Override
	public int updateBanar(Banar banar) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBanar(Banar banar) {
		// TODO Auto-generated method stub
		//�������������ڱ����ѯ���
				int result = 0;
				//����Ԥ���������������������ڽ������ݿ����������
				PreparedStatement pstam = null;				
				try {
					//�������Ӷ����prepareStatement�������õ�Ԥ������󣬸�ֵ��Ԥ����������
					pstam = conn.prepareStatement("DELETE FROM banar WHERE banarid =?");
					pstam.setInt(1, banar.getBanarid());
					
					//����Ԥ��������executeQuery������
					result = pstam.executeUpdate();
					//�����ѯ�����Ϊ�գ���ȡ��������еĸ����ֶΣ���װ���û�����ĸ��������У����ж���ŵ�������
						
				}catch (SQLException e) {
					//��������쳣������쳣��Ϣ
					System.out.println("ɾ��banar����.������Ϣ�� ��" + e.getMessage());			
				} finally {
					//�������ݿ⹤���࣬�رս�����������������
					DBUtils.closeStatement(null, pstam);
				}
			    //���ز�ѯ�����û��б�
				return result;
		
		
	}

	@Override
	public Banar findOneBanar(Banar banar) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
