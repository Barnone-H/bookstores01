package com.kth.kthstore.web.servlet.manager;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.domain.Notice;
import com.kth.kthstore.service.NoticeService;

/**
 * 
 *	��̨�޸Ĺ����servlet
 */
public class EditNoticeServlet extends HttpServlet{

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
		Notice bean = new Notice();
		//��ȡ������
		int n_id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String details = req.getParameter("details");
		
		//����ǰʱ����Ϊ��ӹ����ʱ��
		String t = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		
		bean.setN_id(n_id);
		bean.setTitle(title);
		bean.setDetails(details);
		bean.setN_time(t);
		
		//����dao�㷽��
		nService.updateNotice(bean);
		
		req.getRequestDispatcher("/manager/ListNoticeServlet").forward(req, resp);
	}
}
