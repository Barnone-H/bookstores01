package com.kth.kthstore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.domain.Product;
import com.kth.kthstore.exception.FindProductByIdException;
import com.kth.kthstore.service.ProductService;
/**
 * ������Ʒid����ָ����Ʒ��Ϣ��servlet
 */
public class FindProductByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// �õ���Ʒ��id
		String id = request.getParameter("id");
		// ��ȡtype����ֵ���˴���type����������ͨ�û��ͳ����û�
		String type = request.getParameter("type");		
		ProductService service = new ProductService();		
		try {
			// ����service�㷽����ͨ��id������Ʒ
			Product p = service.findProductById(id);
			request.setAttribute("p", p);
			// ��ͨ�û�Ĭ�ϲ�����typeֵ������ת��info.jspҳ��
			if (type == null) {
				request.getRequestDispatcher("/client/info.jsp").forward(request,response);
				return;
			}						
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(request, response);
			return;
		} catch (FindProductByIdException e) {
			e.printStackTrace();
		}
	}
}
