package com.kth.kthstore.web.servlet.manager;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.domain.Product;
import com.kth.kthstore.exception.ListProductException;
import com.kth.kthstore.service.ProductService;
/**
 * ��̨ϵͳ
 * ��ѯ������Ʒ��Ϣ��servlet
 */
public class ListProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.����service��Ķ���
		ProductService service = new ProductService();
		try {
			// 2.����service�����ڲ�ѯ������Ʒ�ķ���
			List<Product> ps = service.listAll();
			// 3.����ѯ����������Ʒ�Ž�request����
			request.setAttribute("ps", ps);
			// 4.�ض���list.jspҳ��
			request.getRequestDispatcher("/admin/products/list.jsp").forward(
					request, response);
			return;
		} catch (ListProductException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
			return;
		}
	}
}
