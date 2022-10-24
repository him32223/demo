package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Service;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRepository;

@Controller
public class controller {
	@Autowired
	private Service Service;
        

	// get homepage
		@GetMapping("/")
		public String homepage() {
			return "signup";
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
		public String getDashboardPage(Model model, @CurrentSecurityContext(expression = "authentication?.name") String username) {
			List<User> users = Service.retrieveAllUserProfile();
			model.addAttribute("users", users);
			
			//retrieve loggedinuser id
			User loggedInUser = Service.getUserByUsername(username);
			model.addAttribute("person", loggedInUser);
			
			
			return "dashboard";
		}
		
		//get profile page
		@GetMapping("/profile")
		public String getProfilePage(Model model, @RequestParam("id") Integer user_id) {
			User user = Service.getUserById(user_id);
			model.addAttribute("user", user);
			return "profile";
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

	@PostMapping("/update-profile")
	    public String updateUserProfile(Model model, @ModelAttribute("user") User tmp, @RequestParam("id") Integer user_id) {
	        User user = Service.getUserById(user_id);

	        user.setFirstname(tmp.getFirstname());
	        user.setLastname(tmp.getLastname());
	        user.setCompany(tmp.getCompany());
	        user.setCity(tmp.getCity());
	        user.setCountry(tmp.getCountry());

	        Service.saveUser(user);

	        return "redirect:dashboard";
	    }
			
			
			



	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		if(Service.verify(code)) {
			return "verify_success";
		} else {
			return "verify_fail";
		}
	}
  }
