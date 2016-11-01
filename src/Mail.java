
//需要用户名密码邮件发送实例
//本实例以新浪邮箱为例，你需要在新浪后台设置POP3/SMTP服务开启
//更多邮件示例- http://www.magicsite.cn/java/java-sending-email.html

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
	
	public static void main(String[] args) {	
		Mail mail = new Mail();
		mail.sendMail();	
	}

	public void sendMail() {
		// 收件人电子邮箱
		String to = "kangandming@sina.com";

		// 发件人电子邮箱
		String from = "kangandming@sina.com";

		// 指定发送邮件的主机
		String host = "smtp.sina.com";

		// 获取系统属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.smtp.host", host);

		properties.put("mail.smtp.auth", "true");
		// 获取默认session对象
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kangandming@sina.com", "密码"); // 发件人邮件用户名、密码
			}
		});

		try {
			// 创建默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);

			// Set From: 头部头字段
			message.setFrom(new InternetAddress(from));

			// Set To: 头部头字段
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: 头部头字段
			message.setSubject("头部头字段-标题");

			// 设置消息体
			message.setText("内容-hello world！");

			// 发送消息
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}