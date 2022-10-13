package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class controller {
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
	

}
