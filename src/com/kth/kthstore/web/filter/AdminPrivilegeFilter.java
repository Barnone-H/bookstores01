package com.kth.kthstore.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kth.kthstore.domain.User;

public class AdminPrivilegeFilter implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		// 1 ǿ��ת��
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 2�ж��Ƿ����Ȩ��
		User user = (User) request.getSession().getAttribute("user");

		if (user != null && "�����û�".equals(user.getRole())) {
			// 3.����
			chain.doFilter(request, response);
			return;
		}

		response.sendRedirect(request.getContextPath() + "/error/privilege.jsp");

	}

	public void destroy() {

	}

}
