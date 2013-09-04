package com.palmelf.eoffice.action.system;

import com.palmelf.core.util.StringUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.SysConfig;
import com.palmelf.eoffice.service.system.AppUserService;
import com.palmelf.eoffice.service.system.SysConfigService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import nl.captcha.Captcha;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


public class LoginAction extends BaseAction {
	private AppUser user;
	private String username;
	private String password;
	private String checkCode;
	private String key = "RememberAppUser";

	@Resource
	private AppUserService userService;

	@Resource
	private SysConfigService sysConfigService;

	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager = null;

	public String login() {
		StringBuffer msg = new StringBuffer("{msg:'");

		SysConfig sysConfig = this.sysConfigService.findByKey("codeConfig");

		Captcha captcha = (Captcha) getSession().getAttribute("simpleCaptcha");
		Boolean login = Boolean.valueOf(false);

		String newPassword = null;

		if ((!"".equals(this.username)) && (this.username != null)) {
			setUser(this.userService.findByUserName(this.username));

			if (this.user != null) {
				if (org.apache.commons.lang.StringUtils
						.isNotEmpty(this.password)) {
					newPassword = StringUtil.encryptSha256(this.password);
					if (this.user.getPassword().equalsIgnoreCase(newPassword)) {
						if (sysConfig.getDataValue().equals("1")) {
							if (captcha == null) {
								msg.append("请刷新验证码再登录.'");
							} else if (captcha.isCorrect(this.checkCode)) {
								if (this.user.getStatus().shortValue() == 1)
									login = Boolean.valueOf(true);
								else
									msg.append("此用户已被禁用.'");
							} else
								msg.append("验证码不正确.'");

						} else if (this.user.getStatus().shortValue() == 1)
							login = Boolean.valueOf(true);
						else
							msg.append("此用户已被禁用.'");
					} else
						msg.append("密码不正确.'");
				} else {
					msg.append("密码不能为空.'");
				}
			} else
				msg.append("用户不存在.'");
		}
		if (login.booleanValue()) {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					this.username, this.password);
			SecurityContext securityContext = SecurityContextHolder
					.getContext();
			securityContext.setAuthentication(this.authenticationManager
					.authenticate(authRequest));
			SecurityContextHolder.setContext(securityContext);
			getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME",
					this.username);
			String rememberMe = getRequest().getParameter(
					"_spring_security_remember_me");
			if ((rememberMe != null) && (rememberMe.equals("on"))) {
				long tokenValiditySeconds = 1209600L;
				long tokenExpiryTime = System.currentTimeMillis()
						+ tokenValiditySeconds * 1000L;

				String signatureValue = DigestUtils.md5Hex(this.username + ":"
						+ tokenExpiryTime + ":" + this.user.getPassword() + ":"
						+ this.key);

				String tokenValue = this.username + ":" + tokenExpiryTime + ":"
						+ signatureValue;
				String tokenValueBase64 = new String(
						Base64.encodeBase64(tokenValue.getBytes()));
				getResponse().addCookie(
						makeValidCookie(tokenExpiryTime, tokenValueBase64));
			}

			setJsonString("{success:true}");
		} else {
			msg.append(",failure:true}");
			setJsonString(msg.toString());
			return "login";
		}
		return "index";
	}

	private Cookie makeValidCookie(long expiryTime, String tokenValueBase64) {
		HttpServletRequest request = getRequest();
		Cookie cookie = new Cookie("SPRING_SECURITY_REMEMBER_ME_COOKIE",
				tokenValueBase64);
		cookie.setMaxAge(157680000);
		cookie.setPath(org.springframework.util.StringUtils.hasLength(request
				.getContextPath()) ? request.getContextPath() : "/");
		return cookie;
	}

	public AppUser getUser() {
		return this.user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

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

	public String getCheckCode() {
		return this.checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
