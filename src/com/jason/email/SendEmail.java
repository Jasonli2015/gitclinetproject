package com.jason.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class SendEmail {
	
	private static String fileName = "email.properties";
	
	public static void main(String[] args) throws AddressException, MessagingException {	
				
		PropertiesConfiguration config = null;
		
		String htmlPath = "";
		
		String attachmentList[][] = {
				{"#customer.f_name", "Jason"},
				{"#customer.l_name", "Li"},
				{"#customer.account_name", "Lxw824"},
				{"#customer.username", "80546690"},
				{"#customer.company_Ename", "PCCW"},
				{"#customer.company_address", "Hongkong"},
				{"#customer.billing_address", "Hongkong"},
				{"#customer.email", "674478903@qq.com"},
				{"#customer.phone", "18888888888"}
		};
		
		String[] subjectMessage = {};
		
		String attachment = "";
		
		String attachments[] = new String[1];	        
		
		
		try {
			config = new PropertiesConfiguration(fileName);	
			
			attachment = config.getString("ATTACHMENTPATH")+File.separator+"kobe.jpg";
			
			attachments[0] = attachment;	
					
			htmlPath = config.getString("HTMLTEMPLATEDIR")+File.separator+"regiester_success.html";
			
			subjectMessage = readAndReplaceHtmlEmailTemplate(attachmentList,htmlPath);	
					
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		
		//send email
		sendEmail("Email Test",subjectMessage[0],subjectMessage[1],attachments);
	}
	
	public static void sendEmail(String comment,String subject, String content, String[] subjectMessage){
		
		String emailFrom = "";
		
		String emailTo = "";
		
		String emailCc = "";
		
		String emailBcc = "";
		
		String emailGetway = "";
		
		String emailPassword = "";
		
		String emailPort = "";
		
		String emailTransportProtocol = "";
		
		String emailSmtpAuth = "";	
		
		String emailTitle = "Send Mail Test";
		
		PropertiesConfiguration config = null;
		
		MyAuthenticator authenticator = null;
		
		Properties props = new Properties();
		
		Session sendMailSession = null;
		
		Message mailMessage = null;
		
		Address from = null;
		
		//MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象 
        MimeMultipart minemultipart = new MimeMultipart();
        //创建一个包含HTML内容的MimeBodyPart  
        MimeBodyPart html = new MimeBodyPart();        
        		
		try {
			config = new PropertiesConfiguration(fileName);
			
			emailGetway = config.getString("EMAIL_GATEWAY");
			
			emailFrom = config.getString("EMAIL_FROM");
			
			emailPassword = config.getString("EMAIL_PASSWORD");
			
			emailTo = config.getString("EMAIL_TO");
			
			emailCc = config.getString("EMAIL_CC");
			
			emailBcc = config.getString("EMAIL_BCC");
			
			emailPort = config.getString("EMAIL_PORT");
			
			emailTransportProtocol = config.getString("EMAIL_TRANSPORT_PROTOCOL");
			
			emailSmtpAuth = config.getString("EMAIL_SMTP_AUTH");
			
			props.setProperty("mail.host", emailGetway);
			
			props.setProperty("mail.transport.protocol", emailTransportProtocol);
			
			props.setProperty("mail.smtp.auth", emailSmtpAuth);
			
			props.put("mail.smtp.port", emailPort);
			
			//身份认证
			authenticator = new MyAuthenticator(emailFrom, emailPassword);
			//根据邮件会话属性和密码验证器构造一个发送邮件的session    
			sendMailSession = Session.getDefaultInstance(props,authenticator);
			//根据session创建一个邮件消息    
			mailMessage = new MimeMessage(sendMailSession);
			//创建邮件发送者地址
			from = new InternetAddress(emailFrom);
			//设置邮件消息的发送者  
			mailMessage.setFrom(from);
			//创建邮件的接收者地址，并设置到邮件消息中    
			/*InternetAddress[] sendTo = new InternetAddress[emailTo.length];
		    for (int i = 0; i < emailTo.length; i++){
		    	sendTo[i] = new InternetAddress(emailTo[i]);
		    }
			//Message.RecipientType.TO属性表示接收者的类型为TO   
			mailMessage.setRecipients(Message.RecipientType.TO, sendTo);*/ 
			
			InternetAddress[] sendTo = InternetAddress.parse(emailTo);
			mailMessage.setRecipients(Message.RecipientType.TO, sendTo);
			
			/******** Set the coding of the email as UTF-8 */
			html.setContent(content,"text/html;charset=UTF-8");	 
			//设置邮件主题
			mailMessage.setSubject(emailTitle);
			//设置邮件内容
	        minemultipart.addBodyPart(html);
			
			/* Set the attachment by using the array attachmentList which includes the path of attachment ********/
	        if(null != subjectMessage && subjectMessage.length > 0){
	            
	        	MimeBodyPart[] mbp = new MimeBodyPart[subjectMessage.length];
	        	
	            FileDataSource[] fds = new FileDataSource[subjectMessage.length];
	            
	            for(int i = 0;i < subjectMessage.length; i++){
	                mbp[i] = new MimeBodyPart();
	                fds[i] = new FileDataSource(subjectMessage[i]);
	                mbp[i].setDataHandler(new DataHandler(fds[i]));
	                mbp[i].setFileName(fds[i].getName());
	                minemultipart.addBodyPart(mbp[i]);
	            }
	        }
	        
	        /*if(emailCc.length!=0){
	        	InternetAddress[] ccTo = new InternetAddress[emailCc.length];
			    for (int i = 0; i < emailCc.length; i++){
			    	ccTo[i] = new InternetAddress(emailCc[i]);
			    } 
            	mailMessage.setRecipients(Message.RecipientType.CC, ccTo);
            }*/	        
	        
			
			if(!"".equals(emailCc.trim())){
				InternetAddress[] ccTo = InternetAddress.parse(emailCc);
				mailMessage.setRecipients(Message.RecipientType.CC, ccTo);
			}
			
			if(!"".equals(emailBcc.trim())){
				InternetAddress[] BccTo = InternetAddress.parse(emailBcc);
				mailMessage.setRecipients(Message.RecipientType.BCC, BccTo);
			}			
	        
	        //将MiniMultipart对象设置为邮件内容    
	        mailMessage.setContent(minemultipart);    
	        //发送邮件    
	        Transport.send(mailMessage);  
	        
	        System.out.println("邮件发送成功！");
			
		} catch (ConfigurationException e) {
			e.printStackTrace();
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}	
			
	} 
	 
	public static String[] readAndReplaceHtmlEmailTemplate(String[][] replaceString, String htmlPath){
    	
    	//result[0] is email subject, result[1] is email content
    	String[] result = new String[2];
    	
    	try{
    		//路径通配符查找器
    		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();  
        	Resource r = resolver.getResource(htmlPath);
    		
        	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(r.getFile()), "UTF-8"));
			
	    	StringBuffer suject = new StringBuffer();
	    	StringBuffer content = new StringBuffer();
			
			boolean end = false;
			boolean firstSubject = true;
			String str = null;
			
			while(!end){
				str = br.readLine();
				
				if(null != str){
					//split subject from the template
					if(str.contains("Title")){
						String[] subjects = str.trim().split("<{1}/?[\\w =\"]*>{1}");
						for(int j=0;j<subjects.length;j++){
							if(!"".equals(subjects[j].trim())){
								suject.append(subjects[j].trim());
							}
						}
						if(firstSubject){
							suject.append(" : ");
							firstSubject = false;
						}
					}
					
					//replace message
					String[] change;
					for(int i=0; i<replaceString.length; i++){
						change = replaceString[i];
						if(str.contains(change[0])){
							str = str.replace(change[0], change[1]);
						}
					}
					content.append(str).append("\r\n");
				}else{
					end = true;
				}
			}
			
			result[0] = suject.toString();
			result[1] = content.toString();
			
			br.close();
    	 }catch(Exception e){
    		System.out.println(e.getMessage());
         }
    	 
		return result;
    	
    }

}
