package com.palmelf.core.engine;

import java.io.File;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.htmlparser.Parser;
import org.htmlparser.visitors.HtmlPage;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MailEngine {
	private final Log logger = LogFactory.getLog(MailEngine.class);
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	private String defaultFrom;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	public void setFrom(String from) {
		this.defaultFrom = from;
	}

	public void sendMessage(SimpleMailMessage msg, String templateName,
			Map model) {
		String result = null;
		try {
			result = VelocityEngineUtils.mergeTemplateIntoString(
					this.velocityEngine, templateName, model);
		} catch (VelocityException e) {
			e.printStackTrace();
			this.logger.error(e.getMessage());
		}

		msg.setText(result);
		send(msg);
	}

	public void send(SimpleMailMessage msg) throws MailException {
		try {
			this.mailSender.send(msg);
		} catch (MailException ex) {
			this.logger.error(ex.getMessage());
			throw ex;
		}
	}

	public void sendMessage(String[] recipients, String sender,
			ClassPathResource resource, String bodyText, String subject,
			String attachmentName) throws MessagingException {
		MimeMessage message = ((JavaMailSenderImpl) this.mailSender)
				.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(recipients);

		if (sender == null)
			helper.setFrom(this.defaultFrom);
		else {
			helper.setFrom(sender);
		}

		helper.setText(bodyText);
		helper.setSubject(subject);

		helper.addAttachment(attachmentName, resource);

		((JavaMailSenderImpl) this.mailSender).send(message);
	}

	public String sendMimeMessage(String from, String[] tos, String cc,
			String replyTo, String subject, String htmlMsgContent,
			String[] attachedFileNames, File[] attachedFiles, boolean inline) {
		if ((tos == null) || (tos.length == 0) || (tos[0] == null)
				|| ("".equals(tos[0]))) {
			if (this.logger.isErrorEnabled()) {
				this.logger
						.error("Recipient found empty while sending a email, no further processing. Mail subject is:"
								+ subject);
			}
			return "Recipient is empty";
		}

		MimeMessage message = this.mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,
					attachedFiles != null);

			helper.setFrom(from == null ? this.defaultFrom : from);
			helper.setTo(tos);
			if ((cc != null) && (!"".equals(cc))) {
				helper.setCc(cc);
			}
			if ((replyTo != null) && (!"".equals(replyTo))) {
				helper.setReplyTo(replyTo);
			}

			helper.setSubject(subject);

			helper.setText(htmlMsgContent, true);

			if (attachedFiles != null) {
				if (inline) {
					for (int i = 0; i < attachedFiles.length; i++)
						helper.addInline(attachedFileNames[i], attachedFiles[i]);
				} else {
					for (int i = 0; i < attachedFiles.length; i++) {
						helper.addAttachment(attachedFileNames[i],
								attachedFiles[i]);
					}
				}

			}

			this.mailSender.send(message);
			if (this.logger.isDebugEnabled())
				this.logger.debug("A email has been sent successfully to: "
						+ StringUtils.join(tos, ','));
		} catch (Throwable e) {
			this.logger.error("Error occured when sending email.", e);
			return "Error occured when sending email." + e.getMessage();
		}

		return null;
	}

	public String sendTemplateMail(String templateName,
			Map<String, Object> model, String subject, String from,
			String[] tos, String cc, String replyTo,
			String[] attachedFileNames, File[] attachedFiles, boolean inline) {
		String mailContent = null;
		String mailSubject = subject;
		try {
			mailContent = VelocityEngineUtils.mergeTemplateIntoString(
					this.velocityEngine, templateName, model);
			if (subject == null) {
				Parser myParser = Parser.createParser(mailContent, "UTF-8");
				HtmlPage visitor = new HtmlPage(myParser);
				myParser.visitAllNodesWith(visitor);
				mailSubject = visitor.getTitle();
			}
		} catch (Throwable e) {
			throw new RuntimeException(
					"Email template processing error, Check log for detail infomation. Template path: "
							+ templateName, e);
		}

		return sendMimeMessage(from, tos, cc, replyTo, mailSubject,
				mailContent, attachedFileNames, attachedFiles, inline);
	}
}
