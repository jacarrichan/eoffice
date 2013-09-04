package com.palmelf.core.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nl.captcha.Captcha;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.DefaultTextProducer;

public class SimpleCaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARAM_HEIGHT = "height";
	private static final String PARAM_WIDTH = "width";
	protected int _width = 125;
	protected int _height = 50;

	@Override
	public void init() throws ServletException {
		if (getInitParameter(PARAM_HEIGHT) != null) {
			this._height = Integer.valueOf(getInitParameter(PARAM_HEIGHT))
					.intValue();
		}

		if (getInitParameter(PARAM_WIDTH) != null)
			this._width = Integer.valueOf(getInitParameter(PARAM_WIDTH)).intValue();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		char[] number = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Captcha captcha = new Captcha.Builder(this._width, this._height)
				.addText(new DefaultTextProducer(4, number)).addBackground()
				.addNoise().build();

		CaptchaServletUtil.writeImage(resp, captcha.getImage());
		req.getSession().setAttribute("simpleCaptcha", captcha);
	}
}
