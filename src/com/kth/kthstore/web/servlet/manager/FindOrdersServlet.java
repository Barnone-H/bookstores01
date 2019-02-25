package com.kth.kthstore.web.servlet.manager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.domain.Order;
import com.kth.kthstore.service.OrderService;
//�������ж���
public class FindOrdersServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ����Service�����
		OrderService service = new OrderService();
		// ����Service������findAllOrder()������ѯ�����б�
		List<Order> orders = service.findAllOrder();
		//����ѯ���Ķ�����Ϣ��ӵ�request������
		request.setAttribute("orders", orders);
		// ������ת����list.jspҳ��
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request,response);
	}
}
