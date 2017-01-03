package org.iweb.sys.mail;

import java.util.ArrayList;
import java.util.List;

public class SysMail {
	private static MailSenderInfo mail_sys;
	static {
		mail_sys = new MailSenderInfo();
		mail_sys.setMailServerHost("smtp.qkj.com.cn");
		mail_sys.setMailServerPort("25");
		mail_sys.setValidate(true);
		mail_sys.setUserName("360appraisals@qkj.com.cn");
		mail_sys.setPassword("Iloveqkj2646it");// 您的邮箱密码
		mail_sys.setFromAddress("360appraisals@qkj.com.cn");
	}

	public static boolean sendHtml(List<String> to_addr, String subject, String content) {
		mail_sys.setToAddress(to_addr);
		mail_sys.setSubject(subject);
		mail_sys.setContent(content);
		return SimpleMailSender.sendHtmlMail(mail_sys);
	}

	public static boolean sendText(List<String> to_addr, String subject, String content) {
		mail_sys.setToAddress(to_addr);
		mail_sys.setSubject(subject);
		mail_sys.setContent(content);
		return SimpleMailSender.sendTextMail(mail_sys);
	}

	public static void main(String[] args) {
		// 这个类主要是设置邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.qkj.com.cn");
		mailInfo.setMailServerPort("25");
		mailInfo.setValidate(true);
		mailInfo.setUserName("sunshanshan@qkj.com.cn");
		mailInfo.setPassword("Sun786742");// 您的邮箱密码
		mailInfo.setFromAddress("sunshanshan@qkj.com.cn");
		List<String> toa=new ArrayList<String>();
		toa.add("sunshanshan@qkj.com.cn");
		toa.add("liuqianru@qkj.com.cn");
		mailInfo.setToAddress(toa);
		mailInfo.setSubject("测试文本邮件");

		String c = "您有一个活动申请需要审核,活动申请号:S121212121212<br />";
		c += "申请部门:XXXXX   申请人:XXXX<br />";
		c += "主題:为了世界的和平<br />";
		c += "我方总共费用合计:20000 客户总共费用合计:40000";

		mailInfo.setContent(c);
		// 这个类主要来发送邮件
		// SimpleMailSender sms = new SimpleMailSender();
		// 发送文体格式
		// SimpleMailSender.sendTextMail(mailInfo);
		// 发送html格式
		SimpleMailSender.sendHtmlMail(mailInfo);
	}
}
