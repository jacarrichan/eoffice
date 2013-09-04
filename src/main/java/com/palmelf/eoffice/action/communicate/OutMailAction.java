package com.palmelf.eoffice.action.communicate;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.UIDFolder;
import javax.mail.URLName;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


import com.palmelf.core.command.QueryFilter;
import com.palmelf.core.util.AppUtil;
import com.palmelf.core.util.BeanUtil;
import com.palmelf.core.util.CertUtil;
import com.palmelf.core.util.ContextUtil;
import com.palmelf.core.util.FileUtil;
import com.palmelf.core.web.action.BaseAction;
import com.palmelf.eoffice.model.communicate.OutMail;
import com.palmelf.eoffice.model.communicate.OutMailFolder;
import com.palmelf.eoffice.model.communicate.OutMailUserSeting;
import com.palmelf.eoffice.model.system.AppUser;
import com.palmelf.eoffice.model.system.FileAttach;
import com.palmelf.eoffice.service.communicate.OutMailFolderService;
import com.palmelf.eoffice.service.communicate.OutMailService;
import com.palmelf.eoffice.service.communicate.OutMailUserSetingService;
import com.palmelf.eoffice.service.system.AppUserService;
import com.palmelf.eoffice.service.system.FileAttachService;
import com.sun.mail.pop3.POP3Folder;
import com.sun.net.ssl.internal.ssl.Provider;

public class OutMailAction extends BaseAction {
	static long FOLDER_ID_RECEIVE = 1L;
	static long FOLDER_ID_SEND = 2L;
	static long FOLDER_ID_DRAFT = 3L;
	static long FOLDER_ID_DELETE = 4L;
	static long FOLDER_TYPE_OTHER = 10L;
	static short OTHER_FOLDER_TYPE = 10;

	static int FIRST_LEVEL = 1;
	static long FIRST_PARENTID = 0L;
	static short HAVE_DELETE = 1;
	static short NOT_DELETE = 0;
	static short HAVE_READ = 1;
	static short NOT_READ = 0;
	static Short HAVE_REPLY = 1;
	static short NOT_REPLY = 0;
	static String sendType = "smtp";

	static String FILE_PATH_ROOT = AppUtil.getAppAbsolutePath()
			+ "attachFiles/";
	static String OUT_MAIL_TEMP = OutMailAction.FILE_PATH_ROOT + "outMailTemp/";

	@Resource
	private OutMailService outMailService;
	private OutMail outMail;

	@Resource
	private FileAttachService fileAttachService;

	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	private OutMailUserSeting outMailUserSeting;

	@Resource
	private AppUserService appUserService;
	private AppUser appUser;

	@Resource
	private OutMailFolderService outMailFolderService;
	private OutMailFolder outMailFolder;
	private Long mailId;
	private String outMailIds;
	private Long fileId;
	private Long folderId;

	public OutMailUserSeting getOutMailUserSeting() {
		return this.outMailUserSeting;
	}

	public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
		this.outMailUserSeting = outMailUserSeting;
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Long getFileId() {
		return this.fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getOutMailIds() {
		return this.outMailIds;
	}

	public void setOutMailIds(String outMailIds) {
		this.outMailIds = outMailIds;
	}

	public Long getFolderId() {
		return this.folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getMailId() {
		return this.mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public OutMail getOutMail() {
		return this.outMail;
	}

	public void setOutMail(OutMail outMail) {
		this.outMail = outMail;
	}

	public String list() {
		if ((this.folderId == null)
				|| (this.folderId.longValue() == OutMailAction.FOLDER_ID_RECEIVE)) {
			this.setFolderId(new Long(OutMailAction.FOLDER_ID_RECEIVE));
		}

		QueryFilter filter = new QueryFilter(this.getRequest());

		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_outMailFolder.folderId_L_EQ",
				this.folderId.toString());

		filter.addSorted("mailId", "desc");
		List list = this.outMailService.getAll(filter);

		this.getOutMailJsonStr(list, filter.getPagingBean().getTotalItems());

		return "success";
	}

	public String multiDel() {
		OutMailFolder deleteFolder = (OutMailFolder) this.outMailFolderService
				.get(Long.valueOf(OutMailAction.FOLDER_ID_DELETE));

		String[] ids = this.getRequest().getParameterValues("ids");

		if (ids != null) {
			if (this.getFolderId().longValue() == OutMailAction.FOLDER_ID_DELETE) {
				for (String id : ids) {
					this.outMail = ((OutMail) this.outMailService.get(new Long(
							id)));

					if (this.outMail == null) {
						continue;
					}
					Set<FileAttach> outMailFiles = this.outMail
							.getOutMailFiles();
					this.outMailService.remove(this.outMail);

					if ((outMailFiles != null) && (outMailFiles.size() > 0)) {
						for (FileAttach f : outMailFiles) {
							this.delPhysicalFile(f);
							this.fileAttachService.remove(f);
						}
					}

				}

			} else {
				for (String id : ids) {
					this.outMail = ((OutMail) this.outMailService.get(new Long(
							id)));

					this.outMail.setOutMailFolder(deleteFolder);
					this.outMailService.save(this.outMail);
				}
			}
		}

		this.jsonString = "{success:true}";

		return "success";
	}

	public String get() {
		String opt = this.getRequest().getParameter("opt");

		if ((opt == null) || ("".equals(opt))) {
			this.outMail = ((OutMail) this.outMailService.get(this.mailId));
			this.getRequest().setAttribute("__haveNextOutMailFlag", "");
		} else {
			String folderId = this.getRequest().getParameter("folderId");
			if ((folderId == null) || ("".equals(folderId))) {
				folderId = String.valueOf(OutMailAction.FOLDER_ID_RECEIVE);
			}

			List list = null;

			QueryFilter filter = new QueryFilter(this.getRequest());
			filter.getPagingBean().setPageSize(1);

			filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
					.toString());

			filter.addFilter("Q_outMailFolder.folderId_L_EQ", folderId);

			if (opt.equals("_next")) {
				filter.addFilter("Q_mailId_L_GT", this.mailId.toString());
				list = this.outMailService.getAll(filter);
				if (filter.getPagingBean().getStart().intValue() + 1 == filter
						.getPagingBean().getTotalItems()) {
					this.getRequest().setAttribute("__haveNextOutMailFlag",
							"endNext");
				}
			} else if (opt.equals("_pre")) {
				filter.addFilter("Q_mailId_L_LT", this.mailId.toString());
				filter.addSorted("mailId", "desc");
				list = this.outMailService.getAll(filter);
				if (filter.getPagingBean().getStart().intValue() + 1 == filter
						.getPagingBean().getTotalItems()) {
					this.getRequest().setAttribute("__haveNextOutMailFlag",
							"endPre");
				}

			}

			if (list.size() > 0) {
				this.outMail = ((OutMail) list.get(0));
			} else {
				this.outMail = ((OutMail) this.outMailService.get(this.mailId));
			}
		}

		this.setOutMail(this.outMail);
		this.outMail.setReadFlag(Short.valueOf(OutMailAction.HAVE_READ));
		this.outMailService.save(this.outMail);

		if (this.outMail.getFolderId().longValue() == OutMailAction.FOLDER_ID_DRAFT) {
			List list = new ArrayList();
			list.add(this.outMail);
			this.setJsonString(this.getOutMailJsonStr(list, 1));
			return "success";
		}
		if ((this.outMail.getReceiverNames() == null)
				|| (this.outMail.getReceiverNames().equals("null"))) {
			this.outMail.setReceiverNames("(收信人未写)");
		}
		this.getRequest().setAttribute("outMail_view", this.outMail);
		this.getRequest().setAttribute("outMailFiles",
				this.outMail.getOutMailFiles());
		return "detail";
	}

	public String save() {
		this.logger.debug("save start...");

		this.setJsonString("{success:true}");

		String opt = this.getRequest().getParameter("opt");

		this.appUser = ((AppUser) this.appUserService.get(new Long(ContextUtil
				.getCurrentUserId().longValue())));
		this.outMailUserSeting = this.outMailUserSetingService
				.getByLoginId(ContextUtil.getCurrentUserId());
		this.outMailUserSeting.setAppUser(this.appUser);
		this.outMail.setUserId(this.outMailUserSeting.getAppUser().getUserId());
		this.outMail
				.setSenderAddresses(this.outMailUserSeting.getMailAddress());
		this.outMail.setSenderName(this.outMailUserSeting.getUserName());
		this.outMail.setMailDate(new Date());
		this.outMail.setReceiverAddresses(OutMailAction
				.getAddressesToStr(this.outMail.getReceiverNames()));
		this.outMail.setReceiverNames(OutMailAction.getNamesToStr(this.outMail
				.getReceiverNames()));
		this.outMail.setcCAddresses(OutMailAction
				.getAddressesToStr(this.outMail.getcCNames()));
		this.outMail.setcCNames(OutMailAction.getNamesToStr(this.outMail
				.getcCNames()));
		this.outMail.setReadFlag(new Short("0"));
		this.outMail.setReplyFlag(new Short("0"));

		Set outMailFiles = new HashSet();
		String[] fileIds = this.outMail.getFileIds().split(",");
		for (String id : fileIds) {
			if ((!id.equals("")) && (!id.equals("null"))) {
				outMailFiles.add((FileAttach) this.fileAttachService
						.get(new Long(id)));
			}
		}
		this.outMail.setOutMailFiles(outMailFiles);
		try {
			if ((opt != null) && (opt.trim().equals("attach"))) {
				this.outMailFolder = ((OutMailFolder) this.outMailFolderService
						.get(Long.valueOf(OutMailAction.FOLDER_ID_DRAFT)));
				this.outMail.setOutMailFolder(this.outMailFolder);
				this.outMailService.save(this.outMail);
			} else {
				OutMail newOutMail = new OutMail();
				BeanUtil.copyNotNullProperties(newOutMail, this.outMail);
				if ((newOutMail.getContent() == null)
						|| (newOutMail.getContent().equals(""))) {
					newOutMail.setContent("\t\t");
				}

				newOutMail.setMailId(null);
				this.outMailFolder = ((OutMailFolder) this.outMailFolderService
						.get(Long.valueOf(OutMailAction.FOLDER_ID_SEND)));
				newOutMail.setOutMailFolder(this.outMailFolder);

				List reviceList = this.getEMailStrToList(
						newOutMail.getReceiverAddresses(),
						newOutMail.getReceiverNames());
				List ccList = null;

				if ((newOutMail.getcCAddresses() != null)
						&& (!newOutMail.getcCAddresses().trim().equals(""))
						&& (!newOutMail.getcCAddresses().trim().equals("null"))
						&& (newOutMail.getcCAddresses().length() > 2)) {
					ccList = this.getEMailStrToList(
							newOutMail.getcCAddresses(),
							newOutMail.getcCNames());
				}

				this.send(newOutMail, reviceList, ccList,
						this.outMailUserSeting);
				this.outMailService.save(newOutMail);
			}

		} catch (Exception e) {
			e.printStackTrace();
			this.setJsonString("{failure:true,msg:'发送邮件失败!请检查书写的邮件格式是否正确!!'}");
			return "success";
		}
		this.logger.debug("save end...");
		return "success";
	}

	public String attach() {
		String fileIds = this.getRequest().getParameter("fileIds");
		String filenames = this.getRequest().getParameter("filenames");
		this.setOutMail((OutMail) this.outMailService.get(this.mailId));
		Set mailAttachs = this.outMail.getOutMailFiles();
		FileAttach remove = (FileAttach) this.fileAttachService
				.get(this.fileId);
		this.delPhysicalFile(remove);
		mailAttachs.remove(remove);
		this.outMail.setOutMailFiles(mailAttachs);
		this.outMail.setFileIds(fileIds);
		this.outMail.setFileNames(filenames);
		this.outMailService.save(this.outMail);
		this.fileAttachService.remove(this.fileId);
		return "success";
	}

	public String move() {
		StringBuffer msg = new StringBuffer("{");
		if ((this.outMailIds != null) && (this.outMailIds.length() > 0)
				&& (this.folderId != null)) {
			OutMailFolder moveToFolder = (OutMailFolder) this.outMailFolderService
					.get(new Long(this.folderId.longValue()));

			String[] ids = this.outMailIds.split(",");
			boolean moveSuccess = true;
			if ((ids != null) && (ids.length > 0)) {
				if (moveSuccess) {
					for (String id : ids) {
						if (!"".equals(id)) {
							this.outMail = ((OutMail) this.outMailService
									.get(new Long(id)));
							this.outMail.setOutMailFolder(moveToFolder);
							this.outMailService.save(this.outMail);
						}
					}

					msg.append("success:true}");
					this.setJsonString(msg.toString());
				} else {
					msg.append("failure:true}");
					this.setJsonString(msg.toString());
				}
			}
		} else {
			msg.append("msg:'请选择要移动的邮件及文件夹!'");
			msg.append(",failure:true}");
			this.setJsonString(msg.toString());
		}

		return "success";
	}

	public String opt() {
		this.setOutMail((OutMail) this.outMailService.get(this.mailId));
		String opt = this.getRequest().getParameter("opt");

		this.outMail.setReadFlag(Short.valueOf(OutMailAction.HAVE_READ));
		if ((opt != null) && (opt.trim().equals("回复"))) {
			this.outMail.setReplyFlag(OutMailAction.HAVE_REPLY);
		}

		this.outMailService.save(this.outMail);

		StringBuffer newSubject = new StringBuffer(
				"<br><br><br><br><br><br><br><hr>");
		newSubject.append("<br>----<strong>" + opt + "邮件</strong>----");
		newSubject.append("<br><strong>发件人</strong>:"
				+ this.outMail.getSenderName());
		newSubject.append("<br><strong>发送时间</strong>:"
				+ this.outMail.getMailDate());
		newSubject.append("<br><strong>收件人</strong>:"
				+ this.outMail.getReceiverNames());
		String copyToNames = this.outMail.getcCNames();
		if ((!"".equals(copyToNames)) && (copyToNames != null)) {
			newSubject.append("<br><strong>抄送人</strong>:" + copyToNames);
		}
		newSubject.append("<br><strong>主题</strong>:" + this.outMail.getTitle());
		newSubject.append("<br><strong>内容</strong>:<br><br>"
				+ this.outMail.getContent());
		this.outMail.setContent(newSubject.toString());
		this.outMail.setTitle(opt + ":" + this.outMail.getTitle());

		List list = new ArrayList();
		list.add(this.outMail);

		this.setJsonString(this.getOutMailJsonStr(list, 1));
		return "success";
	}

	protected String getOutMailJsonStr(List<OutMail> list, int totalCounts) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		StringBuffer buff = new StringBuffer("{success:true,\"totalCounts\":")
				.append(totalCounts).append(",result:[");
		for (OutMail mailTemp : list) {
			buff.append("{")
					.append("\"mailId\":")
					.append(mailTemp.getMailId())
					.append(",\"title\":")
					.append("\""
							+ OutMailAction.changSpecialCharacters(mailTemp
									.getTitle()) + "\"")
					.append(",\"content\":")
					.append("\""
							+ OutMailAction.changSpecialCharacters(mailTemp
									.getContent()) + "\"")
					.append(",\"senderAddresses\":")
					.append("\"" + mailTemp.getSenderAddresses() + "\"")
					.append(",\"receiverAddresses\":")
					.append("\"" + mailTemp.getReceiverAddresses() + "\"")
					.append(",\"cCAddresses\":")
					.append("\"" + mailTemp.getcCAddresses() + "\"")
					.append(",\"cCNames\":")
					.append("\""
							+ OutMailAction.changSpecialCharacters(mailTemp
									.getcCNames()) + "\"")
					.append(",\"receiverNames\":")
					.append("\""
							+ OutMailAction.changSpecialCharacters(mailTemp
									.getReceiverNames()) + "\"")
					.append(",\"senderName\":")
					.append("\""
							+ OutMailAction.changSpecialCharacters(mailTemp
									.getSenderName()) + "\"")
					.append(",\"mailDate\":")
					.append("\"" + df.format(mailTemp.getMailDate()) + "\"")
					.append(",\"readFlag\":")
					.append(mailTemp.getReadFlag())
					.append(",\"replyFlag\":")
					.append(mailTemp.getReplyFlag())
					.append(",\"fileIds\":")
					.append("\"" + mailTemp.getFileIds() + "\"")
					.append(",\"fileNames\":")
					.append("\""
							+ OutMailAction.changSpecialCharacters(mailTemp
									.getFileNames()) + "\"");

			buff.append("},");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");

		this.jsonString = buff.toString();
		return this.jsonString;
	}

	protected static String changSpecialCharacters(String special) {
		if (special == null) {
			return "";
		}

		special = special.replace("\"", "'").replace("\t", "\\t")
				.replace("\n", "\\n").replace(":", "\\:").replace("[", "\\[")
				.replace("]", "\\]").replace("{", "\\{").replace("}", "\\}")
				.replace(",", "\\,").replace("\r", "\\r").replace("null", "");

		return special;
	}

	protected void send(OutMail outMail_, List reviceList, List ccList,
			OutMailUserSeting _outMailUserSeting) throws Exception {
		this.logger.debug("send start...");

		String user = _outMailUserSeting.getMailAddress();
		String pass = _outMailUserSeting.getMailPass();
		String smtpHost = _outMailUserSeting.getSmtpHost();
		String smtpPort = _outMailUserSeting.getSmtpPort();
		String smtpAuth = "true";
		this.logger.debug(_outMailUserSeting);

		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtpHost);
		props.setProperty("mail.smtp.port", smtpPort);
		props.put("mail.smtp.auth", smtpAuth);

		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);

		File cert = null;

		cert = CertUtil.get(smtpHost, Integer.parseInt(smtpPort));

		if (cert != null) {
			this.logger.debug("ssl connection...");
			Security.addProvider(new Provider());
			String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			props.setProperty("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			System.setProperty("javax.net.ssl.trustStore",
					cert.getAbsolutePath());
			props.put("javax.net.ssl.trustStore", cert.getAbsolutePath());
		} else {
			this.logger.debug("plaintext connection or tls connection...");
			props.put("mail.smtp.starttls.enable", "true");
		}

		final String username = user;
		final String password = pass;

		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		this.logger.debug("connetion session:" + session);
		EmailAddress sender = new EmailAddress(
				_outMailUserSeting.getMailAddress(),
				_outMailUserSeting.getUserName());
		BodyPart contentPart = new MimeBodyPart();

		contentPart.setHeader("Content-Transfer-Encoding", "base64");
		contentPart
				.setContent(outMail_.getContent(), "text/html;charset=utf-8");

		MimeMessage message = new MimeMessage(session);
		Multipart multipart = new MimeMultipart();
		message.setSubject(outMail_.getTitle(), "utf-8");

		message.setText("utf-8", "utf-8");
		message.setSentDate(outMail_.getMailDate() == null ? new Date()
				: outMail_.getMailDate());

		multipart.addBodyPart(contentPart);

		message.setFrom(sender.toInternetAddress());

		InternetAddress[] address = OutMailAction.getAddressByType(reviceList);
		if (address != null) {
			message.addRecipients(Message.RecipientType.TO, address);
		}
		if ((ccList != null) && (ccList.size() > 0)) {
			address = OutMailAction.getAddressByType(ccList);
			if (address != null) {
				message.addRecipients(Message.RecipientType.CC, address);
			}

		}

		if ((outMail_.getFileIds() != null)
				&& (outMail_.getFileIds().length() > 0)) {
			String fileids = outMail_.getFileIds();
			String[] fileid_s = fileids.split(",");
			for (int i = 0; i < fileid_s.length; i++) {
				if ((!fileid_s[i].equals("")) && (!fileid_s[i].equals("null"))) {
					FileAttach f__attach = (FileAttach) this.fileAttachService
							.get(new Long(fileid_s[i]));
					if (f__attach == null) {
						continue;
					}
					File file = new File(OutMailAction.FILE_PATH_ROOT
							+ f__attach.getFilePath());

					BodyPart messageBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(file);
					messageBodyPart.setDataHandler(new DataHandler(source));
					messageBodyPart.setFileName(MimeUtility.encodeWord(
							f__attach.getFileName(), "UTF-8", "Q"));
					multipart.addBodyPart(messageBodyPart);
				}

			}

		}

		message.setContent(multipart);
		if (OutMailAction.sendType == null) {
			OutMailAction.sendType = "smtp";
		}

		Transport transport = session.getTransport(OutMailAction.sendType);
		transport.connect(_outMailUserSeting.getSmtpHost().toString(),
				username, password);
		Transport.send(message);
		transport.close();
		this.logger.debug("send end");
	}

	protected static InternetAddress[] getAddressByType(List<EmailAddress> list)
			throws Exception {
		if (list != null) {
			InternetAddress[] address = new InternetAddress[list.size()];
			for (int i = 0; i < list.size(); i++) {
				if (((EmailAddress) list.get(i)).toInternetAddress() != null) {
					address[i] = ((EmailAddress) list.get(i))
							.toInternetAddress();
				}
			}

			return address;
		}
		return null;
	}

	protected static String getAddressesToStr(String str) {
		String address = "";
		if ((str != null) && (str.length() > 0) && (str.indexOf(";") >= 0)) {
			String[] emails = str.split(";");
			for (String email2 : emails) {
				if ((email2.length() > 1) && (email2.indexOf("<") >= 0)
						&& (email2.indexOf(">") > 0)) {
					String[] email = email2.split("<");

					address = address
							+ email[1].substring(0, email[1].length() - 1)
							+ ",";
				} else {
					address = address + email2 + ",";
				}
			}

		} else if ((str != null) && (str.indexOf("<") >= 0)
				&& (str.indexOf(">") > 0)) {
			String[] email = str.split("<");

			address = address + email[1].substring(0, email[1].length() - 1)
					+ ",";
		} else {
			address = str + ",";
		}

		if ((address != null) && (address.length() > 1)) {
			address = address.substring(0, address.length() - 1);
		}
		return address;
	}

	protected static String getNamesToStr(String str) {
		String name = "";
		if ((str != null) && (str.length() > 0) && (str.indexOf(";") >= 0)) {
			String[] emails = str.split(";");
			for (String email2 : emails) {
				if ((email2.indexOf("<") >= 0) && (email2.indexOf(">") > 0)) {
					String[] email = email2.split("<");
					name = name + email[0] + ",";
				} else {
					name = name + email2 + ",";
				}

			}

		} else if ((str != null) && (str.indexOf("<") >= 0)
				&& (str.indexOf(">") > 0)) {
			String[] email = str.split("<");
			name = name + email[0] + ",";
		} else {
			name = str + ",";
		}

		if ((name != null) && (name.length() > 1)) {
			name = name.substring(0, name.length() - 1);
		}
		if (name == null) {
			name = " ";
		}
		return name;
	}

	protected List<EmailAddress> getEMailStrToList(String addresses,
			String names) {
		List list = new ArrayList();

		if ((addresses != null) && (addresses.length() > 1)) {
			String[] revice_a = addresses.split(",");
			if ((names != null) && (names.length() > 1)) {
				String[] revice_n = names.split(",");

				for (int i = 0; i < revice_a.length; i++) {
					if ((revice_a[i] != null) && (revice_a[i].length() > 1)
							&& (revice_a[i].indexOf("@") > 0)) {
						if (revice_n.length > i) {
							EmailAddress add = new EmailAddress(
									revice_a[i].trim(), revice_n[i]);
							list.add(add);
						} else {
							EmailAddress add = new EmailAddress(
									revice_a[i].trim(), revice_a[i].trim());
							list.add(add);
						}
					} else {
						this.setJsonString("{failure:true,msg:'收件人地址有误!'}");
						return null;
					}
				}
			} else {
				for (String element : revice_a) {
					if ((element != null) && (element.length() > 1)
							&& (element.indexOf("@") > 0)) {
						EmailAddress add = new EmailAddress(element.trim(),
								element.trim());
						list.add(add);
					} else {
						return null;
					}
				}
			}
		} else {
			this.setJsonString("{failure:true,msg:'收件人不能为空!'}");
			return null;
		}

		return list;
	}

	public String fetch() {
		this.logger.debug("fectch start...");
		this.outMailUserSeting = this.outMailUserSetingService
				.getByLoginId(ContextUtil.getCurrentUserId());

		if (this.outMailUserSeting != null) {
			this.logger.debug(this.outMailUserSeting);

			Properties props = new Properties();

			props.setProperty("mail.pop3.socketFactory.fallback", "false");
			props.setProperty("mail.pop3.port",
					this.outMailUserSeting.getPopPort());
			props.setProperty("mail.pop3.socketFactory.port",
					this.outMailUserSeting.getPopPort());

			File cert = null;

			cert = CertUtil.get(this.outMailUserSeting.getPopHost(),
					Integer.parseInt(this.outMailUserSeting.getPopPort()));

			if (cert != null) {
				Security.addProvider(new Provider());
				this.logger.debug("ssl connection...");
				String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
				props.setProperty("mail.pop3.socketFactory.class",
						"javax.net.ssl.SSLSocketFactory");
				System.setProperty("javax.net.ssl.trustStore",
						cert.getAbsolutePath());
				props.put("javax.net.ssl.trustStore", cert.getAbsolutePath());
			} else {
				this.logger.debug("plaintext connection or tls connection...");
				props.put("mail.smtp.starttls.enable", "true");
			}

			Session session = Session.getInstance(props, null);

			URLName urln = new URLName("pop3",
					this.outMailUserSeting.getPopHost(),
					Integer.parseInt(this.outMailUserSeting.getPopPort()),
					null, this.outMailUserSeting.getMailAddress(),
					this.outMailUserSeting.getMailPass());

			Store store = null;
			POP3Folder inbox = null;

			OutMailFolder fectchFolder = (OutMailFolder) this.outMailFolderService
					.get(Long.valueOf(OutMailAction.FOLDER_ID_RECEIVE));
			try {
				store = session.getStore(urln);
				store.connect();
				inbox = (POP3Folder) store.getFolder("INBOX");
				inbox.open(1);
				FetchProfile profile = new FetchProfile();

				profile.add(UIDFolder.FetchProfileItem.UID);
				Message[] messages = inbox.getMessages();
				inbox.fetch(messages, profile);

				Map uidMail = this.outMailService.getUidByUserId(ContextUtil
						.getCurrentUserId());

				int count = messages.length;
				this.logger.debug("mail counts :\t" + count);

				for (int i = 0; i < count; i++) {
					if (uidMail.get(inbox.getUID(messages[i])) == null) {
						this.logger.debug("mail uid:"
								+ inbox.getUID(messages[i]));
						OutMail fetchOutMail = new OutMail();

						fetchOutMail.setUid(inbox.getUID(messages[i]));

						String from = messages[i].getFrom()[0].toString();
						InternetAddress ia = new InternetAddress(from);
						String senerAddress = ia.getAddress();
						if ((senerAddress == null) || (senerAddress.equals(""))) {
							senerAddress = "\t";
						}
						fetchOutMail.setSenderAddresses(senerAddress);
						fetchOutMail.setSenderName(ia.getPersonal());

						InternetAddress[] ia_re = (InternetAddress[]) null;
						try {
							ia_re = (InternetAddress[]) messages[i]
									.getRecipients(Message.RecipientType.TO);
						} catch (AddressException e) {
							e.printStackTrace();
						}
						String re_a = "\t";
						String re_n = "\t";

						if ((ia_re != null) && (ia_re.length > 0)) {
							for (int k = 0; k < ia_re.length; k++) {
								re_a = re_a + ia_re[k].getAddress() + ",";
								if ((ia_re[k].getPersonal() != null)
										&& (!ia_re[k].getPersonal().equals(""))) {
									re_n = re_n + ia_re[k].getPersonal() + ",";
								} else {
									re_n = re_n + ia_re[k].getAddress() + ",";
								}
							}

							if ((re_a != null) && (re_a.length() > 1)) {
								re_a = re_a.substring(0, re_a.length() - 1);
							}
							if ((re_n != null) && (re_n.length() > 1)) {
								re_n = re_n.substring(0, re_n.length() - 1);
							}

						}

						fetchOutMail.setReceiverAddresses(re_a);
						fetchOutMail.setReceiverNames(re_n);

						InternetAddress[] ia_cc = (InternetAddress[]) null;
						try {
							ia_cc = (InternetAddress[]) messages[i]
									.getRecipients(Message.RecipientType.CC);
						} catch (AddressException e) {
							e.printStackTrace();
						}
						String cc_a = "\t";
						String cc_n = "\t";
						if ((ia_cc != null) && (ia_cc.length > 0)) {
							for (InternetAddress element : ia_cc) {
								cc_a = cc_a + element.getAddress() + ",";
								cc_n = cc_n + element.getPersonal() + ",";
							}
							if ((cc_a != null) && (cc_a.length() > 1)) {
								cc_a = cc_a.substring(0, cc_a.length() - 1);
							}
							if ((cc_n != null) && (cc_n.length() > 1)) {
								cc_n = cc_n.substring(0, cc_n.length() - 1);
							}

						}

						fetchOutMail.setcCAddresses(cc_a);
						fetchOutMail.setcCNames(cc_n);

						InternetAddress[] ia_bcc = (InternetAddress[]) null;
						try {
							ia_bcc = (InternetAddress[]) messages[i]
									.getRecipients(Message.RecipientType.BCC);
						} catch (AddressException e) {
							e.printStackTrace();
						}
						String bcc_a = "\t";
						String bcc_n = "\t";
						if ((ia_bcc != null) && (ia_bcc.length > 0)) {
							for (InternetAddress element : ia_bcc) {
								bcc_a = bcc_a + element.getAddress() + ",";
								bcc_n = bcc_n + element.getPersonal() + ",";
							}
							if ((bcc_a != null) && (bcc_a.length() > 1)) {
								bcc_a = bcc_a.substring(0, bcc_a.length() - 1);
							}
							if ((bcc_n != null) && (bcc_n.length() > 1)) {
								bcc_n = bcc_n.substring(0, bcc_n.length() - 1);
							}

						}

						fetchOutMail.setbCCAddresses(bcc_a);
						fetchOutMail.setbCCAnames(bcc_n);

						fetchOutMail.setTitle(messages[i].getSubject());
						this.logger.debug("mail title:\t"
								+ messages[i].getSubject());

						Date sentDate = null;
						if (messages[i].getSentDate() != null) {
							sentDate = messages[i].getSentDate();
						} else {
							sentDate = new Date();
						}
						fetchOutMail.setMailDate(sentDate);

						String content = this.getMailContent(messages[i]);
						if ((content == null) || (content.equals(""))) {
							content = "\t";
						}

						fetchOutMail.setContent(content);

						Set outMailFiles = this.saveFileForFetch(messages[i]);
						fetchOutMail.setOutMailFiles(outMailFiles);

						fetchOutMail.setReadFlag(new Short("0"));
						fetchOutMail.setReplyFlag(new Short("0"));

						fetchOutMail.setOutMailFolder(fectchFolder);

						fetchOutMail.setUserId(ContextUtil.getCurrentUserId());

						this.outMailService.save(fetchOutMail);

						this.outMail = ((OutMail) this.outMailService
								.get(fetchOutMail.getMailId()));

						Set<FileAttach> outMailFiles_new = this.outMail
								.getOutMailFiles();

						String f_id = "";
						String f_name = "";
						if ((outMailFiles_new != null)
								&& (outMailFiles_new.size() > 0)) {
							for (FileAttach f : outMailFiles_new) {
								f_id = f_id + f.getFileId().toString() + ",";
								f_name = f_name + f.getFileName().toString()
										+ ",";
							}
							if (f_id.length() > 1) {
								f_id = f_id.substring(0, f_id.length() - 1);
							}

							if (f_name.length() > 1) {
								f_name = f_name.substring(0,
										f_name.length() - 1);
							}
						}

						this.outMail.setFileIds(f_id);
						this.outMail.setFileNames(f_name);
						this.outMailService.save(this.outMail);
						this.logger.debug("save mail sucess :"
								+ this.outMail.getMailId());
					}

				}

			} catch (MessagingException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					inbox.close(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					store.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}

		this.setJsonString("{success:true,msg:'收取邮件完成！'}");
		this.logger.debug("fectch end...");
		return "success";
	}

	protected boolean delPhysicalFile(FileAttach fileAttach) {
		String fp_p = OutMailAction.FILE_PATH_ROOT;
		String fp_full = fp_p + fileAttach.getFilePath();
		File del_f = new File(fp_full);
		if (del_f.delete()) {
			this.logger.info("删除文件：" + fp_full);
		} else {
			this.logger.info("文件不存在：" + fp_full);
		}
		return true;
	}

	protected static String decodeText(String text) {
		try {
			if (text == null) {
				return "";
			}
			if ((text.startsWith("=?GB")) || (text.startsWith("=?gb"))) {
				text = MimeUtility.decodeText(text);
			} else if ((text.startsWith("=?ISO-8859-1"))
					|| (text.startsWith("=?iso-8859-1"))) {
				text = MimeUtility.decodeText(text);
			} else {
				text = new String(text.getBytes("ISO8859_1"));
			}
		} catch (Exception e) {
			System.out.println("转换字附编号出错!");
			e.printStackTrace();
		}
		return text;
	}

	protected String getMailContent(Part part) {
		String userName = ContextUtil.getCurrentUser().getUsername();
		StringBuffer sb = new StringBuffer();
		sb.append(new String(""));
		try {
			if ((part.isMimeType("text/plain"))
					|| (part.isMimeType("text/html"))) {
				sb.append(part.getContent());
			} else {
				String str1;
				if (part.isMimeType("multipart/*")) {
					if (part.isMimeType("multipart/alternative")) {
						Multipart mp = (Multipart) part.getContent();
						int index = 0;
						if (mp.getCount() > 1) {
							index = 1;
						}

						Part tmp = mp.getBodyPart(index);
						sb.append(tmp.getContent());
					} else {
						if (part.isMimeType("multipart/related")) {
							Multipart mp = (Multipart) part.getContent();
							Part tmp = mp.getBodyPart(0);
							String body = this.getMailContent(tmp);
							int count = mp.getCount();

							for (int k = 1; (count > 1) && (k < count); k++) {
								Part att = mp.getBodyPart(k);
								String attname = att.getFileName();
								if (attname != null) {
									attname = MimeUtility.decodeText(attname);
								} else {
									attname = "\t";
								}
								try {
									File attFile = new File(
											OutMailAction.FILE_PATH_ROOT,
											userName.concat(attname));

									this.logger
											.debug("附件：" + attFile.getPath());
									attFile.createNewFile();
									FileOutputStream fileoutput = new FileOutputStream(
											attFile);
									InputStream is = att.getInputStream();
									BufferedOutputStream outs = new BufferedOutputStream(
											fileoutput);
									byte[] b = new byte[att.getSize()];
									is.read(b);
									outs.write(b);
									outs.close();
								} catch (Exception e) {
									this.logger
											.error("Error occurred when to get the photos from server");
									e.printStackTrace();
								}
								String[] Content_ID = att
										.getHeader("Content-ID");
								if ((Content_ID != null)
										&& (Content_ID.length > 0)) {
									String cid_name = Content_ID[0].replaceAll(
											"<", "").replaceAll(">", "");
									body = body.replaceAll(
											"cid:" + cid_name,
											OutMailAction.FILE_PATH_ROOT
													.concat("/")
													.concat(userName
															.concat(attname)));
								}
							}

							sb.append(body);
							str1 = sb.toString();
							return str1;
						}

						Multipart mp = (Multipart) part.getContent();
						Part tmp = mp.getBodyPart(0);
						str1 = this.getMailContent(tmp);
						return str1;
					}
				} else {
					if (part.isMimeType("message/rfc822")) {
						str1 = this.getMailContent((Message) part.getContent());
						return str1;
					}

					Object obj = part.getContent();
					if ((obj instanceof String)) {
						sb.append(obj);
					} else {
						Multipart mp = (Multipart) obj;
						Part tmp = mp.getBodyPart(0);
						str1 = this.getMailContent(tmp);
						return str1;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "解析正文错误!";
		} finally {
			part = null;
			System.gc();
		}
		part = null;
		System.gc();

		return sb.toString();
	}

	protected Set<FileAttach> saveFileForFetch(Message msg) throws IOException,
			MessagingException {
		String contentType = msg.getContentType();

		if (contentType.startsWith("multipart/mixed")) {
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM");

			Set outMailFiles = new HashSet();

			Multipart multipart = (Multipart) msg.getContent();

			int j = 0;
			for (int n = multipart.getCount(); j < n; j++) {
				Part part = multipart.getBodyPart(j);

				String disposition = part.getDisposition();
				if (disposition != null) {
					FileAttach fileAttach = new FileAttach();
					fileAttach.setCreatetime(new Date());
					fileAttach.setCreator(ContextUtil.getCurrentUser()
							.getFullname());
					this.logger.debug("part.getFileName()="
							+ part.getFileName());
					String fileName = part.getFileName();
					if (fileName != null) {
						fileName = MimeUtility.decodeText(fileName);
					} else {
						fileName = "\t";
					}
					String[] ext = fileName.split("\\.");
					fileAttach.setFileName(fileName);
					fileAttach.setExt(ext[(ext.length - 1)]);
					fileAttach.setFileType("communicate/outmail/download");
					fileAttach.setNote(String.valueOf(part.getSize())
							+ " bytes");
					String ym = df.format(new Date());
					InputStream in = part.getInputStream();

					String fp_p = OutMailAction.FILE_PATH_ROOT;
					String fp_c = "communicate\\outmail\\download\\"
							+ ContextUtil.getCurrentUser().getUsername() + "\\"
							+ ym + "\\";
					fp_p = fp_p.replace("\\", "/");
					fp_c = fp_c.replace("\\", "/");

					String filePath = FileUtil.generateFilename(fileAttach
							.getFileName());
					filePath = filePath.substring(filePath.indexOf("/") + 1,
							filePath.length());

					fileAttach.setFilePath(fp_c + filePath.trim());
					File f = new File(fp_p, fp_c);
					if ((!f.exists()) && (!f.mkdirs())) {
						this.logger.info("目录不存在，创建失败！");
					}

					String f_full_p = fp_p + fileAttach.getFilePath();
					f_full_p = f_full_p.replace("\\", "/");

					FileOutputStream out = new FileOutputStream(f_full_p);
					int data;
					while ((data = in.read()) != -1) {
						out.write(data);
					}
					in.close();
					out.close();
					outMailFiles.add(fileAttach);
				}

			}

			return outMailFiles;
		}
		return null;
	}

	protected class EmailAddress {
		protected String address;
		protected String name;

		public String getAddress() {
			return this.address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getName() {
			return this.name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public EmailAddress() {
		}

		public EmailAddress(String address, String name) {
			this.address = address;
			this.name = name;
		}

		public InternetAddress toInternetAddress() throws Exception {
			if ((this.name != null) && (!this.name.trim().equals(""))) {
				return new InternetAddress(this.address,
						MimeUtility.encodeWord(this.name, "utf-8", "Q"));
			}
			return new InternetAddress(this.address);
		}
	}
}
