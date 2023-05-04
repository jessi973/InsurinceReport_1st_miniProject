package in.divaji.Reports_App01.util;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Emailutils {
	
	@Autowired
	private JavaMailSender javaMailSender; 
	
	public boolean sendEmail(String subject,String body, String to, File file) {
		
		try {
			MimeMessage mimeMessage=javaMailSender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);
			helper.setSubject(subject);
			helper.setText(body ,true);
			helper.setTo(to);
			helper.addAttachment("CitizenPlan", file);
			javaMailSender.send(mimeMessage);
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
		
	}
	

}
