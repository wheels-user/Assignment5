package iss.java.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MyAuthenticator extends Authenticator {

	private String username;
	private String password;
	
	/**
	 * MyAuthenticator 构造函数
	 * @param username 用户名
	 * @param password 密码
	 */
	public MyAuthenticator(String username,String password){
		this.username = username;
		this.password = password;
	}
	
	
	/** 
	 * @return PasswordAuthentication 账号密码信息
	 */
	public PasswordAuthentication getPasswordAuthentication(){
	    return new PasswordAuthentication(username, password);
	  }
	
}
