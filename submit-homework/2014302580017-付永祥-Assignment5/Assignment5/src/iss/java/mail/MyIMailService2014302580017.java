package iss.java.mail;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.FromTerm;
import javax.mail.search.SearchTerm;

public class MyIMailService2014302580017 implements IMailService {
	
	//系统配置属性
	private final static transient Properties props = System.getProperties();
	//用户名
	private static final String username = "15827623303@139.com";
	//密码
	private static final String password = "*************";
	//smtp主机名
	private static final String smtpHostName = "smtp.139.com";
	//imap主机名
	private static final String imapHostName = "imap.139.com";
	
	//登录权限认证
	private MyAuthenticator authenticator;
	//与服务器之间的会议
	private Session session;
	//邮箱中的邮件数目
	private int mailCount = 0;

	
	@Override
	public void connect() throws MessagingException {
		// TODO Auto-generated method stub
		
		
		// 
        props.put("mail.smtp.auth", "true");
        props.put("mail.imap.auth","true");
        props.put("mail.imap.host", imapHostName);
        props.put("mail.store.protocol", "imap");
        props.put("mail.smtp.host", smtpHostName);
        
        
        
        authenticator = new MyAuthenticator(username, password);
        // 
//        System.out.println(authenticator.getPasswordAuthentication().getUserName()+" " +authenticator.getPasswordAuthentication().getPassword());
        session = Session.getInstance(props, authenticator);
//        session.setDebug(true);
        
        
	}

	@Override
	public void send(String recipient, String subject, Object content) throws MessagingException {
		// TODO Auto-generated method stub
		
		MimeMessage message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(username));
		message.setRecipient(Message.RecipientType.TO, 
		  new InternetAddress(recipient));
		message.setSubject(subject);
		message.setText(content.toString());
		
		// Send message
		

		Transport.send(message);
	}	
	
	

	@Override
	public boolean listen() throws MessagingException {
		// TODO Auto-generated method stub
		// Get the store
		Store store = session.getStore("imap");
		 
		store.connect(imapHostName, username, password);

		// Get folder
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		
		if(mailCount == 0)
			mailCount = folder.getUnreadMessageCount();
		
		boolean hasReply = mailCount != folder.getUnreadMessageCount();
		
		folder.close(false);
		store.close();
		
		return hasReply;
	}

	@Override
	public String getReplyMessageContent(String sender, String subject) throws MessagingException, IOException {
		// TODO Auto-generated method stub
		String context = null;
		
		Store store = session.getStore("imap");
		store.connect(imapHostName, username, password);
		
		// Get folder
		Folder folder = store.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		
//		SearchTerm andTerm = new AndTerm(new FromTerm(new InternetAddress(sender))
//				,new SubjectTerm(subject));
		
		SearchTerm ft = new FromTerm(new InternetAddress(sender));
		
		Message[] messages = folder.search(ft); 
		
		
		if(messages.length > 0)
			context = messages[0].getContent().toString();
		
			
		folder.close(false);
		store.close();
		return context;
	}

}
