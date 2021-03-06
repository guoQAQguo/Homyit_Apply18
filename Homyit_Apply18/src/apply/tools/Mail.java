package apply.tools;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
 
/**
 *
 * 测试发送邮件
 */
public class Mail{
	public static void main(String args[]) {
		System.out.println("mmm");
		try {
			String name = "股狗狗";
			String email = "1751064337@qq.com"; 
			sendemail(name,email);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void sendemail(String name,String email) throws MessagingException {
		//创建Properties 类用于记录邮箱的一些属性
		final Properties props = new Properties();
		 // 表示SMTP发送邮件，必须进行身份验证
		props.put("mail.smtp.auth", "true");
		//此处填写SMTP服务器
		props.put("mail.smtp.host", "smtp.qq.com");
		//端口号，QQ邮箱给出了两个端口，但是另一个465一直使用不了，用了就是程序假死的发送失败的，,，所以就给出这一个587。,端口的问题在下面介绍。
		props.put("mail.smtp.port", "587");
		// 此处填写你的账号
		props.put("mail.user", "1751064337@qq.com");
		// 此处的密码就是前面说的16位STMP口令
		props.put("mail.password", "jfnpzbrbduhldhbj");//这里要去掉从QQ邮箱中得到的16位口令中间空格的。
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、密码
			String userName = props.getProperty("mail.user");
			String password = props.getProperty("mail.password");
			return new PasswordAuthentication(userName, password);
			}
		};
//使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession);
		// 设置发件人
		InternetAddress form = new InternetAddress(
		props.getProperty("mail.user"));
		message.setFrom(form);
		// 设置收件人的邮箱
		InternetAddress to = new InternetAddress(email);
		message.setRecipient(RecipientType.TO, to);
		// 设置邮件标题
		message.setSubject("宏奕报名成功");
		// 设置邮件的内容体
		message.setContent("【宏奕】恭喜"+name+",你已经成功报名，后续我们会在通知群中公布分组安排，希望你在后期的培训当中坚持下来，我们与你同在！", "text/html;charset=UTF-8");
		// 最后当然就是发送邮件啦
		Transport.send(message);
		System.out.println("至此QQ邮件发送完毕!");
	}
}
