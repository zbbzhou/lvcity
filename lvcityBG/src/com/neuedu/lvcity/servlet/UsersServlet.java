package com.neuedu.lvcity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.UsersService;
import com.neuedu.lvcity.service.impl.UsersServiceImpl;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/UsersServlet")//servlet�����ã�����Ϊ�û���������Ĳ���
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {  //servelt�Ĺ��췽��
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //����д��һ��ʵ�ַ�������Ϳ����ˣ���һ���������÷���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getParameter("action");
		
		if("login".equals(action)){
			doLogin(request,response);
		}
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//��ȡ����¼����
		String username=request.getParameter("name");
		String passwd=request.getParameter("passwd");
		//����service�еķ���
		UsersService usersService=UsersServiceImpl.getInStance();
		//����ֵ����users����
		Users users=usersService.login(username, passwd);
		//�ж��û��Ƿ����
		if(users==null){
			//�û�������
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}else{
			//�û�����
			HttpSession session=request.getSession();
			session.setAttribute("user", users);
			response.sendRedirect(request.getContextPath()+"/Admin/index.jsp");
					
		}
		
		
		
	}

}
