package com.kth.kthstore.web.servlet.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.service.NoticeService;

/**
 * 
 *	��̨ɾ�������servlet
 */
public class DeleteNoticeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoticeService nService = new NoticeService();
		//��ȡ�������������id
		String n_id = req.getParameter("id");

		//����dao�㷽��
		nService.deleteNotice(n_id);
		
		req.getRequestDispatcher("/manager/ListNoticeServlet").forward(req, resp);
	}
}
