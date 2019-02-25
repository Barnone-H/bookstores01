package com.kth.kthstore.web.servlet.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.domain.Notice;
import com.kth.kthstore.service.NoticeService;
import com.kth.kthstore.service.ProductService;
/**
 *	ǰ̨ҳ��չʾ��servlet 
 *	1��չʾ������ӻ��޸ĵ�һ������
 *  2��չʾ����������Ʒ
 */
public class ShowIndexServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//��ѯ���һ�����棬���ݵ�index.jspҳ�����չʾ
		NoticeService nService = new NoticeService();
		Notice notice = nService.getRecentNotice();
		req.setAttribute("n", notice);
		
		//��ѯ����������������Ʒ�����ݵ�index.jspҳ�����չʾ
		ProductService pService = new ProductService();
		List<Object[]> pList =  pService.getWeekHotProduct();
		/*for(Object[] os:pList){
			for(Object o:os){
				System.out.println(o);
			}
			System.out.println("---------------------");
		}*/
		req.setAttribute("pList", pList);
		
		//����ת��
		req.getRequestDispatcher("/client/index.jsp").forward(req, resp);
	}
}
