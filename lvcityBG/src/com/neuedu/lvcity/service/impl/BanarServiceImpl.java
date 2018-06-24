package com.neuedu.lvcity.service.impl;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.neuedu.lvcity.common.DBUtils;
import com.neuedu.lvcity.dao.BanarDao;
import com.neuedu.lvcity.dao.impl.BanarDaoImpl;
import com.neuedu.lvcity.model.Banar;
import com.neuedu.lvcity.service.BanarService;

public class BanarServiceImpl implements BanarService{
	
	/**
	 * ��ʵ��
	 */
	private static final BanarService instance = new BanarServiceImpl();

	/**
	 * ȡ��ʵ��
	 * 
	 * @return ʵ������
	 */
	public static BanarService getInstance() {
		return instance;
	}

	/**
	 * ���췽��
	 */
	private BanarServiceImpl() {
	}

	@Override
	public int banarCount() {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		int result = 0;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			//����userDao��ʵ�������
			BanarDao banarDao = new BanarDaoImpl(conn);
			
			//����dao�е�banarCount�������������ݿ��ѯ�����������ֵ����ѯ�������
			result = banarDao.banarCount();	
		
		} catch (Exception e) {
			System.out.println("��ѯͳ������banar����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return result;
	}

	@Override
	public List<Banar> findAllBanar(Map<String, Object> map) {
		// TODO Auto-generated method stub
				//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
						Connection conn = null;
						//�������������ڱ������ݿ��ѯ���
						List<Banar> banars = null;
						try{
							//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
							conn = DBUtils.getConnection();
							//����userDao��ʵ�������
							BanarDao banarDao = new BanarDaoImpl(conn);
							//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
							banars = banarDao.findAllBanar(map);			
						
						} catch (Exception e) {
							System.out.println("��ѯ����banar����"+e.getMessage());
						} finally {
							//�������ݿ⹤�����closeConnection�������ر�����
							DBUtils.closeConnection(conn);
						}
						//�������ݿ��ѯ���
						return banars;
	}

	@Override
	public int addBanar(String imagePath) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
		Connection conn = null;
		//�������������ڱ������ݿ��ѯ���
		int result = 0;
		try{
			//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
			conn = DBUtils.getConnection();
			DBUtils.beginTransaction(conn);
			//����userDao��ʵ�������
			BanarDao BanarDao = new BanarDaoImpl(conn);
			//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
			result = BanarDao.addBanar(imagePath);			
		    DBUtils.commit(conn);
		} catch (Exception e) {
			System.out.println("����banar����"+e.getMessage());
		} finally {
			//�������ݿ⹤�����closeConnection�������ر�����
			DBUtils.closeConnection(conn);
		}
		//�������ݿ��ѯ���
		return result;
	}

	@Override
	public int updateBanar(Banar banar) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBanar(Banar banar) {
		//�������ݿ����Ӷ������ڱ������ݿ����Ӷ���
				Connection conn = null;
				//�������������ڱ������ݿ��ѯ���
				int result = 0;
				try{
					//�������ݿ⹤�����getConnection������ȡ�����ݿ����Ӷ��󣬲���ֵ�����ݿ����Ӷ������
					conn = DBUtils.getConnection();
					DBUtils.beginTransaction(conn);
					//����userDao��ʵ�������
					BanarDao BanarDao = new BanarDaoImpl(conn);
					//����dao�е�selectAll�������������ݿ��ѯ�����������ֵ����ѯ�������
					result = BanarDao.deleteBanar(banar);
				    DBUtils.commit(conn);
				} catch (Exception e) {
					System.out.println("ɾ��banar����"+e.getMessage());
				} finally {
					//�������ݿ⹤�����closeConnection�������ر�����
					DBUtils.closeConnection(conn);
				}
				//�������ݿ��ѯ���
				return result;
	}

	@Override
	public Banar findOneBanar(Banar banar) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
