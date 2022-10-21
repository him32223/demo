package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Service;
import com.example.demo.entity.User;

@Controller
public class controller {
	@Autowired
	private Service Service;
        

	// get homepage
		@GetMapping("/")
		public String homepage() {
			return "homepage";
		}
		
		// get about page
		@GetMapping("/about")
		public String getAboutPage() {
			return "about";
		}
		
		// get contact page
		@GetMapping("/contact")
		public String getContactPage() {
			return "contact";
		}
		
		// get signin page
		@GetMapping("/signin")
		public String getSignInPage() {
			return "signin";
		}
		
		// get signup page
		@GetMapping("/signup")
		public String getSignUpPage() {
			return "signup";
		}
		
		// get dashboard page
		@GetMapping("/dashboard")
		public String getDashboardPage() {
			return "dashboard";
		}
		   // post method to process registration
		@PostMapping("/process_signup")
		public String register(Model model, @ModelAttribute("user") User user) {
			
			BCryptPasswordEncoder PasswordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = PasswordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			
			Service.saveUser(user);
			
			return "thankyou";
		}


}
