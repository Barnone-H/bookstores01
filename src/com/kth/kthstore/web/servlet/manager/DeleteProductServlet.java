package com.kth.kthstore.web.servlet.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.kth.kthstore.domain.Product;
import com.kth.kthstore.exception.AddProductException;
import com.kth.kthstore.service.ProductService;
import com.kth.kthstore.utils.FileUploadUtils;
import com.kth.kthstore.utils.IdUtils;

/**
 * ��̨ϵͳ
 * ɾ����Ʒ��Ϣ��servlet
 */
public class DeleteProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ��ȡ�����������Ʒid
		String id = request.getParameter("id");
		ProductService service = new ProductService();
		// ����service��������Ʒ����
		service.deleteProduct(id);
		response.sendRedirect(request.getContextPath() + "/listProduct");
		return;
	}

}
