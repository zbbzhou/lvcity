package com.neuedu.lvcity.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;
import com.neuedu.lvcity.common.FileUtils;
import com.neuedu.lvcity.model.Banar;
import com.neuedu.lvcity.service.BanarService;
import com.neuedu.lvcity.service.impl.BanarServiceImpl;

import jdk.management.resource.internal.inst.FileChannelImplRMHooks;

public class BanarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BanarServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		if ("findAllBanar".equals(action)) {
			dofindAllBanar(request, response);
		} else if ("addBanar".equals(action)) {
			doAddBanar(request, response);
		} else if ("delBanar".equals(action)) {
			doDelBanar(request, response);
		}
	}

	private void doDelBanar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ����Service����
		BanarService banarService = BanarServiceImpl.getInstance();
		int banarid = Integer.parseInt(request.getParameter("banarid"));
		Banar banar = new Banar();
		banar.setBanarid(banarid);
		int res = banarService.deleteBanar(banar);
		System.out.println(res);
		Map<String, Object> map = new HashMap<String, Object>();
		if (res > 0) {
			map.put("success", true);
		} else {
			map.put("success", false);
			map.put("errorMsg", "delete contact fail !!!");
		}

		String str = JSONObject.toJSONString(map);
		response.getWriter().write(str);

	}

	@SuppressWarnings("unchecked")
	private void doAddBanar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// ��ȡsession
		HttpSession se = request.getSession();
		// ����Service����
		BanarService banarService = BanarServiceImpl.getInstance();

		// �ϴ��ļ���Ŀ¼
		//	 String path = request.getSession().getServletContext().getRealPath("/")+"images";    //�ļ�ϵͳ��·��
        // String path= System.getProperty("catalina.home") + "\\webapps\\lvcityFG\\images\\";    //tomcat�����·��
       				
		//�����ļ�����
		File savePath = new File(FileUtils.UPLOAD_PATH);	
		
		// ��������Ƿ����ļ��ϴ�����
		//boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		
		// 1,����DiskFileItemFactory�����࣬������ָ������������һ����ʱĿ¼
		DiskFileItemFactory disk = new DiskFileItemFactory(1024 * 10, savePath);
	
		// 2,����ServletFileUpload�������ϱߵ���ʱ�ļ���Ҳ����Ĭ��ֵ
		ServletFileUpload up = new ServletFileUpload(disk);		

		try {
			// 3,����request
			List<FileItem> list;
			list = up.parseRequest(request);
			// �����һ���ļ���
			FileItem file = list.get(0);
			// ��ȡ�ļ�����
			String fileName = file.getName();
			
			// ��ȡ�ļ������ͣ�
			//String fileType = file.getContentType();
			// �ļ���С
	     	//	int size = file.getInputStream().available();
			
			// ��ȡ�ļ��������������ڶ�ȡҪ�ϴ����ļ�
			InputStream in = file.getInputStream();
			
			// ��������ֽ��������ڽ��ļ��ϴ���Ŀ��λ��
			OutputStream out = new FileOutputStream(new File(savePath + "/" + fileName));
		
			
			// �ļ�copy
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = in.read(b)) != -1) {
				out.write(b, 0, len);
				//out2.write(b, 0, len);
			}
			out.flush();
			out.close();
		

			// ɾ���ϴ����ɵ���ʱ�ļ�
			file.delete();
			
			//�����ݿ���Ӽ�¼����ǰ�����ͼƬ��������ǰ׺			
			int result = banarService.addBanar("http://localhost:9080/uploads/" + fileName);
			
			//���ɷ��ؽ����map
			Map<String, Object> map = new HashMap<String, Object>();
			if (result > 0) {
				map.put("success", true);
			} else {
				map.put("success", false);
				map.put("errorMsg", "Save user fail !");
			}
           //��mapת��JSON����
			String str = JSONObject.toJSONString(map);
			//���ؽ��
			response.getWriter().write(str);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void dofindAllBanar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// page��ҳ�룬��ʼֵ 1�� rows��ÿҳ��ʾ�С� pageΪǰ̨Ҫ��ѯ��ҳ��rowsΪǰ̨��ÿҳ��¼��
		int rows = Integer.parseInt(request.getParameter("rows"));
		// System.out.println(rows);
		int page = Integer.parseInt(request.getParameter("page"));

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("startPage", (page - 1) * rows);
		pageMap.put("endPage", rows);

		// ����Service����
		BanarService banarService = BanarServiceImpl.getInstance();
		//����ָ��ҳ�ļ�¼
		List<Banar> list = banarService.findAllBanar(pageMap);
		// System.out.println(list.size());
		//�����ܼ�¼��
		int total = banarService.banarCount();

		// JSON�У�total��¼������rows��¼��totalΪ��̨���صģ����ݿ�ģ��ܼ�¼�������ڶ�����rowsΪ��̨���ص�json�������顣
		map.put("rows", list);
		map.put("total", total);
		String str = JSONObject.toJSONString(map);
		// System.out.println(map.toString());
		response.getWriter().write(str);

		/*
		 * //��ȡsession HttpSession se = request.getSession(); // ����Service����
		 * BanarService banarService = BanarServiceImpl.getInstance();
		 * List<Banar> banars = null; // ��ѯ�����û� banars =
		 * banarService.findAllBanar(); if(banars != null) { //
		 * System.out.println(banars.size()); se.setAttribute("banarList",
		 * banars); response.sendRedirect(request.getContextPath() +
		 * "/Admin/banar.jsp");
		 * 
		 * }
		 */
	}

}
