package iss.java.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	private String username;
	private String password;
	
	/**
	 * MyAuthenticator ���캯��
	 * @param username �û���
	 * @param password ����
	 */
	public MyAuthenticator(String username,String password){
		this.username = username;
		this.password = password;
	}
	
	
	/** 
	 * @return PasswordAuthentication �˺�������Ϣ
	 */
	public PasswordAuthentication getPasswordAuthentication(){
	    return new PasswordAuthentication(username, password);
	  }
	
}
