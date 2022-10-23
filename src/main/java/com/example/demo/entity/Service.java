package com.example.demo.entity;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;
import net.bytebuddy.utility.RandomString;

@org.springframework.stereotype.Service
@Transactional
public class Service {

	private static String siteURL = "http://localhost:8080";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JavaMailSender mailSender;

	// save/update user object
	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void register(User user, String siteURL) 
		throws MessagingException, UnsupportedEncodingException {

		String randomCode = RandomString.make(64); // generate random verification code
		user.setVerificationCode(randomCode); // set verification code to a user
		user.setEnabled(false); // set enabled as false

		userRepository.save(user); // temporarily create a new user
		sendVerificationEmail(user, Service.siteURL); // let user verify
	}

	private void sendVerificationEmail(User user, String siteURL)
		throws MessagingException, UnsupportedEncodingException {

		String toAddress = user.getEmail();
		String fromAddress = "hadrihilmi@gmail.com";
		String senderName = "LinkedMeIn";
		String subject = "LinkedMeIn - Verify your Registration";

		String content = "Dear [[name]],<br>" +
						"Please click the link below to verify your registration: <br>" + 
						"<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + 
						"Thank you,<br>" + 
						"LinkedMeIn";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		content = content.replace("[[name]]", user.getUsername());
		String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

		content = content.replace("[[URL]]", verifyURL);
		helper.setText(content, true);

		mailSender.send(message);
	}

	public boolean verify(String verificationCode) {
		User user = userRepository.findUserByVerificationCode(verificationCode);

		if(user == null || user.isEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			userRepository.save(user);

			return true;
		}
	}

}