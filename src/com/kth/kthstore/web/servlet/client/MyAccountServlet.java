package com.kth.kthstore.web.servlet.client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.domain.User;

public class MyAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * ���ǰ̨ϵͳ�еġ��ҵ��˻��������������������
		 * 1���û�δ��¼�������¼ҳ��
		 * 2���û��ѵ�¼
		 *   a�������û������뵽��̨ϵͳ
		 *   b����ͨ�û�����¼���ҵ��˻�
		 */
		//��session�в�����Ϊ��user���ĻỰ
		User user = (User) request.getSession().getAttribute("user");
		//����ҵ�û����Ϊ��user���ĻỰ��˵���û�û�е�¼����ʱ��ת����¼ҳ��
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/client/login.jsp");
			return;
		}
		//����ǳ����û������뵽������Ǻ�̨����ϵͳ��������뵽��ͨ�û����˻���Ϣҳ��
		if ("�����û�".equals(user.getRole())) {
			response.sendRedirect(request.getContextPath() + "/admin/login/home.jsp");
//			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/client/myAccount.jsp");
//			return;
		}
	}
}