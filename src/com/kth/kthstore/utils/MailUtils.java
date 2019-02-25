package com.kth.kthstore.utils;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
/**
 * �����ʼ��Ĺ�����
 */
public class MailUtils {
	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {
		// 1.����һ���������ʼ��������Ự���� Session
		Properties props = new Properties();
		// �����ʼ�����Э��ΪSMTP
		props.setProperty("mail.transport.protocol", "SMTP");
		// ����SMTP��������ַ
		props.setProperty("mail.host", "smtp.sohu.com");
		// ����SMTP�������Ƿ���Ҫ�û���֤����Ҫ��֤����Ϊtrue
		props.setProperty("mail.smtp.auth", "true");
		// ������֤��
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("kth222", "1234567890");
			}
		};
		Session session = Session.getInstance(props, auth);
		// 2.����һ��Message�����൱�����ʼ�����
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("kth222@sohu.com")); // ���÷�����
		message.setRecipient(RecipientType.TO, new InternetAddress(email)); // ���÷��ͷ�ʽ�������
		message.setSubject("�û�����");
		// message.setText("����һ�⼤���ʼ�����<a href='#'>���</a>");
		message.setContent(emailMsg, "text/html;charset=utf-8");
		// 3.���� Transport���ڽ��ʼ�����
		Transport.send(message);
	}
}
