package com.palmelf.core.web.filter;

import com.palmelf.core.security.SecurityDataSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class SecurityInterceptorFilter extends OncePerRequestFilter {
	private HashMap<String, Set<String>> roleUrlsMap = null;
	private SecurityDataSource securityDataSource;

	public void setSecurityDataSource(SecurityDataSource securityDataSource) {
		this.securityDataSource = securityDataSource;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String url = request.getRequestURI();

		if (StringUtils.hasLength(request.getContextPath())) {
			String contextPath = request.getContextPath();
			int index = url.indexOf(contextPath);
			if (index != -1) {
				url = url.substring(index + contextPath.length());
			}
		}
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		boolean isSuperUser = false;
		
		for (GrantedAuthority authority : auth.getAuthorities()) {
			if("管理员".equals(authority.getAuthority())){
				isSuperUser = true;
				break;
			}
			if("ROLE_PUBLIC".equals(authority.getAuthority())){
				isSuperUser = true;
			}
		}

		if ((!isSuperUser) && (!isUrlGrantedRight(url, auth))) {
			if (this.logger.isDebugEnabled()) {
				this.logger.info("ungranted url:" + url);
			}
			if (url.startsWith("/mobile/")) {
				response.sendRedirect(request.getContextPath()
						+ "/mobile/login.jsp");
				return;
			}
			throw new AccessDeniedException("Access is denied! Url:"
					+ url
					+ " User:"
					+ SecurityContextHolder.getContext().getAuthentication()
							.getName());
		}

		if (this.logger.isDebugEnabled()) {
			this.logger.debug("pass the url:" + url);
		}

		chain.doFilter(request, response);
	}

	private boolean isUrlGrantedRight(String url, Authentication auth) {
		for (GrantedAuthority ga : auth.getAuthorities()) {
			Set urlSet = this.roleUrlsMap.get(ga.getAuthority());

			if ((urlSet != null) && (urlSet.contains(url))) {
				return true;
			}
		}
		return false;
	}

	public void loadDataSource() {
		this.roleUrlsMap = this.securityDataSource.getDataSource();
	}

	@Override
	public void afterPropertiesSet() throws ServletException {
		loadDataSource();
		if (this.roleUrlsMap == null)
			throw new RuntimeException("没有进行设置系统的权限匹配数据源");
	}
}
