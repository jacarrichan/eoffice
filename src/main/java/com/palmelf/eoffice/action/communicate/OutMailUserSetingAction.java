package com.palmelf.eoffice.action.communicate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.CertUtil;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.communicate.OutMailUserSeting;
import com.palmelf.eoffice.service.communicate.OutMailUserSetingService;
import com.sun.mail.pop3.POP3Folder;
import com.sun.net.ssl.internal.ssl.Provider;
import java.io.File;
import java.lang.reflect.Type;
import java.security.Security;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class OutMailUserSetingAction extends BaseAction {

	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	private OutMailUserSeting outMailUserSeting;

	public OutMailUserSeting getOutMailUserSeting() {
		return this.outMailUserSeting;
	}

	public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
		this.outMailUserSeting = outMailUserSeting;
	}

	String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	@Override
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<OutMailUserSeting> list = this.outMailUserSetingService
				.getAll(filter);

		Type type = new TypeToken<List<OutMailUserSeting>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		this.jsonString = buff.toString();

		return "success";
	}

	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				this.outMailUserSetingService.remove(new Long(id));
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		OutMailUserSeting outMailUserSeting = this.outMailUserSetingService
				.getByLoginId(ContextUtil.getCurrentUserId());

		Gson gson = new Gson();

		StringBuffer sb = new StringBuffer("{success:true,data:");

		if (outMailUserSeting != null) {
			sb.append(gson.toJson(outMailUserSeting));
		} else {
			outMailUserSeting = new OutMailUserSeting();
			outMailUserSeting.setUserId(ContextUtil.getCurrentUserId());
			outMailUserSeting.setUserName(ContextUtil.getCurrentUser()
					.getUsername());

			sb.append(gson.toJson(outMailUserSeting));
		}

		sb.append("}");
		setJsonString(sb.toString());

		return "success";
	}

	@Override
	public String save() {
		boolean send = false;
		boolean fetch = false;

		send = send(this.outMailUserSeting);

		fetch = fetch(this.outMailUserSeting);

		if ((send) && (fetch))
			setJsonString("{success:true}");
		else if ((!send) && (!fetch))
			setJsonString("{failure:true,msg:'连接到smtp,pop服务器失败，请检查书写是否正确!!'}");
		else if (!send)
			setJsonString("{failure:true,msg:'连接到smtp服务器失败，请检查书写是否正确!!'}");
		else if (!fetch) {
			setJsonString("{failure:true,msg:'连接到pop服务器失败，请检查书写是否正确!!'}");
		}
		this.outMailUserSeting.setUserId(ContextUtil.getCurrentUserId());
		this.outMailUserSetingService.save(this.outMailUserSeting);
		this.logger.debug(">>>" + this.outMailUserSeting);
		return "success";
	}

	protected boolean send(OutMailUserSeting os) {
		this.logger.debug("send start...");
		Transport transport = null;
		Session session = null;
		try {
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", os.getSmtpHost());
			props.setProperty("mail.smtp.port", os.getSmtpPort());

			props.put("mail.smtp.auth", "true");

			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			props.setProperty("mail.smtp.socketFactory.port", os.getSmtpPort());

			File certstmp = CertUtil.get(os.getSmtpHost(),
					Integer.parseInt(os.getSmtpPort()));
			if (certstmp != null) {
				this.logger.debug("ssl connection...");
				Security.addProvider(new Provider());
				props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);

				System.setProperty("javax.net.ssl.trustStore",
						certstmp.getAbsolutePath());
				props.setProperty("javax.net.ssl.trustStore",
						certstmp.getAbsolutePath());
			} else {
				this.logger.debug("plaintext connection or tls connection...");
				props.put("mail.smtp.starttls.enable", "true");
			}
			final String username = os.getMailAddress();
			final String password = os.getMailPass();

			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			this.logger.debug("connetion session:" + session);
			MimeMessage message = new MimeMessage(session);
			Multipart multipart = new MimeMultipart();
			message.setSubject("宏天邮件测试", "utf-8");
			BodyPart contentPart = new MimeBodyPart();

			contentPart.setHeader("Content-Transfer-Encoding", "base64");
			contentPart.setContent("宏天邮件测试", "text/html;charset=utf-8");
			message.setText("宏天邮件测试", "utf-8");
			message.setSentDate(new Date());

			multipart.addBodyPart(contentPart);

			message.setFrom(new InternetAddress(os.getMailAddress(),
					MimeUtility.encodeWord(os.getUserName(), "utf-8", "Q")));

			InternetAddress[] address = new InternetAddress[1];
			address[0] = new InternetAddress(os.getMailAddress(),
					MimeUtility.encodeWord(os.getUserName(), "utf-8", "Q"));
			message.addRecipients(Message.RecipientType.TO, address);
			message.saveChanges();

			transport = session.getTransport("smtp");
			transport.connect(os.getSmtpHost().toString(), username, password);

			Transport.send(message);
			this.logger.debug("send end...");
			return true;
		} catch (Exception e) {
			this.logger.info("连接smtp 服务器失败");
			e.printStackTrace();
			return false;
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				this.logger.info("关闭连接失败");
				e.printStackTrace();
			}
		}
	}

	protected boolean fetch(OutMailUserSeting os) {
		this.logger.debug("fectch start...");

		Store store = null;
		POP3Folder inbox = null;
		try {
			File certpop = CertUtil.get(os.getPopHost(),
					Integer.parseInt(os.getPopPort()));

			Properties props = new Properties();

			props.setProperty("mail.pop3.socketFactory.fallback", "false");
			props.setProperty("mail.pop3.port", os.getPopPort());
			props.setProperty("mail.pop3.socketFactory.port", os.getPopPort());

			if (certpop != null) {
				this.logger.debug("ssl connection...");
				Security.addProvider(new Provider());

				props.setProperty("mail.pop3.socketFactory.class", SSL_FACTORY);
				System.setProperty("javax.net.ssl.trustStore",
						certpop.getAbsolutePath());
				props.setProperty("javax.net.ssl.trustStore",
						certpop.getAbsolutePath());
			} else {
				this.logger.debug("plaintext connection or tls connection...");
				props.put("mail.smtp.starttls.enable", "true");
			}

			Session session = Session.getInstance(props, null);

			URLName urln = new URLName("pop3", os.getPopHost(),
					Integer.parseInt(os.getPopPort()), null,
					os.getMailAddress(), os.getMailPass());

			store = session.getStore(urln);
			store.connect();
			inbox = (POP3Folder) store.getFolder("INBOX");
			inbox.open(1);

			Message[] messages = inbox.getMessages();

			int count = messages.length;
			this.logger.debug("mail count:" + count);
			this.logger.debug("fectch end...");
			return count > 0;
		} catch (Exception e) {
			this.logger.info("连接pop 服务器失败");
			e.printStackTrace();
			return false;
		} finally {
			try {
				inbox.close(false);
			} catch (Exception e) {
				this.logger.info("关闭连接失败");
				e.printStackTrace();
			}
			try {
				store.close();
			} catch (Exception e) {
				this.logger.info("关闭连接失败");
				e.printStackTrace();
			}
		}
	}
}
