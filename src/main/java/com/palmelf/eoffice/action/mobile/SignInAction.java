package com.palmelf.eoffice.action.mobile;

import com.palmelf.core.util.StringUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.service.system.AppUserService;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


public class SignInAction extends BaseAction {

	@Resource
	private AppUserService userService;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager = null;
	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String execute() throws Exception {
		if ((StringUtils.isNotEmpty(this.username))
				&& (StringUtils.isNotEmpty(this.password))) {
			AppUser user = this.userService.findByUserName(this.username);
			if (user != null) {
				String enPassword = StringUtil.encryptSha256(this.password);
				if (enPassword.equals(user.getPassword())) {
					UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
							this.username, this.password);
					SecurityContext securityContext = SecurityContextHolder
							.getContext();
					securityContext
							.setAuthentication(this.authenticationManager
									.authenticate(authRequest));
					SecurityContextHolder.setContext(securityContext);

					return "success";
				}
			}
		}

		return "input";
	}
}
