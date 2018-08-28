package com.filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.*;

@WebFilter(filterName = "AuthFilter", urlPatterns = {
		"/*" }, initParams = @WebInitParam(name = "excludePatterns", value = "/,login,logout,login.jsp,login.css,favicon.ico,.svg,400.jsp,401.jsp,403.jsp,404.jsp,500.jsp"))

public class AuthFilter implements Filter {

	String[] excludePatterns;

	public void init(FilterConfig fConfig) throws ServletException {
		excludePatterns = fConfig.getInitParameter("excludePatterns").split(",");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		for (String pattern : excludePatterns) {
			if (req.getRequestURI().endsWith(pattern)) {
				chain.doFilter(request, response);
				return;
			}
		}

		if (session != null && session.getAttribute("username") != null) {
			chain.doFilter(req, res);
			return;
		} else {
			System.out.println("Filtering... " + request.getRemoteAddr() + " is not authenticated");
			res.sendRedirect("/login.jsp");
			return;
		}
	}

	@Override
	public void destroy() {
		excludePatterns = null;
	}
}