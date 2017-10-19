package org.iweb.sys.mail;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class SysMail {
	private static MailSenderInfo mail_sys;
	static {
		mail_sys = new MailSenderInfo();
		mail_sys.setMailServerHost("smtp.mxhichina.com");
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
		/*MailSenderInfo mailInfo = new MailSenderInfo();
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
		SimpleMailSender.sendHtmlMail(mailInfo);*/
		
		
		List<String> toa = new ArrayList<String>();
		toa.add("sunshanshan@qkj.com.cn");
		String sub = "青青稞酒360°领导力评估调查";
		StringBuffer url = new StringBuffer();
			//url.append(url2.substring(0, url2.indexOf(uri)));
			url.append("http://djx.qkj.com.cn");
		url.append("/check360/check_360ScoreLoad?");
		url.append("ic.uuid="+1);//被考核人与考核人中间表
		url.append("&index360.uuid="+ 1);//活动id
		url.append("&viewFlag=mdy");
		
		StringBuffer content = new StringBuffer();
		content.append("<div><div style='margin-left:4%;'>");
		content.append("<p>");
		content.append("尊敬的aa：</p>");
		content.append("<p>");
		content.append("您好：</p>");
		content.append("<p>");
		content.append("我们非常诚挚地邀请您配合完成此次360°领导力评估问卷调查，该调查将帮助公司管理人员真实、准确地了解个人领导力的优势和发展需要。请您于<font style='font-weight: bold;'>2017年9月24日下午6点前</font>完成此次调查问卷的全部填写工作。</p>");
		content.append("<p style='text-indent: 2em;'>1.本次360°领导力评估问卷调查的内容，将以“青青稞酒领导力素质模型”为标准，具体内容请查阅附件。</p>");
		content.append("<p style='text-indent: 2em;'>2.除被评估者的上级反馈外，系统将确保所有其他人的反馈<font style='font-weight: bold;'>绝对保持匿名，</font>你的反馈将与其他人的反馈一起通过系统进行平均。</p>");
		content.append("<p style='text-indent: 2em;'>3.您坦率、客观的评分与反馈将有助于被评估者清楚地了解自己的领导力现状，并为其提供有针对性的发展建议。</p>");
		content.append("<p style='text-indent: 2em;'>4.填写问卷时，请考虑被评估者<font style='font-weight: bold;'>在过去6个月的整体工作和能力表现，</font>其中包括会议、合作项目、日常工作和达成目标中表现出来的所有行为，而非仅仅关注在过去的某个单独事件。</p>");
		
		content.append("<p style='text-indent: 2em;'>请点击链接填写问卷：</p>");
		content.append("<table  cellpadding='0' cellspacing='0' border='1'><tr><td>被评价对象</td><td align='center'>问卷链接</td></tr><tr><td>关阳</td><td>链接地址：<a style='text-decoration: none;' href='"
				+ url.toString()
				+ "'>"
				+ url.toString()
				+ "</a></td></tr></table>");
		
	/*	content.append("<p style='text-indent: 2em;display: block;word-break: break-all;'>");
		content.append("链接地址：<a style='text-decoration: none;' href='"
				+ url.toString()
				+ "'>"
				+ url.toString()
				+ "</a></p>");*/
		content.append("</div>");
		content.append("<P style='font-weight: bold;'>注意事项：</P>");
		content.append("<P>•如果在邮件中直接点击链接无法打开，请完整拷贝链接粘贴到浏览器地址栏中。</P>");
		content.append("<P>•请尽量一次性完整填写问卷并提交，提交后填答信息不可再更改。</P>");
		content.append("<P>•如中途不小心退出了系统，可点击链接重新作答。</P>");
		
		content.append("<P>如在问卷调查过程中有任何问题，请及时与<font style='font-weight: bold;'>人力资源中心包正梅（7713264）</font>联系，我们会尽快为您提供有效的服务和支持。衷心感谢您对此项工作的支持与配合！</P>");
		content.append("<p  align='right'>人力资源中心</p>");
		content.append("<p  align='right'>2017年9月18日</p>");
		content.append("</div>");
		if(toa.size()>0){
			SysMail.sendHtml(toa, sub, content.toString());
		}
	}
}
