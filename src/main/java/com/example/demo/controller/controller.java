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
		public String registerUser(Model model, @ModelAttribute("user") User user) {
			
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = user.getPassword();
			user.setPassword(encodedPassword);
			
			
			
			System.out.println("username:" + user.getUsername());
			System.out.println("email:" + user.getEmail());
			System.out.println("password:" + user.getPassword());
			Service.saveUser(user); // we dont have any checking if user exist for now, but later
			return "thankyou";
		}

}
