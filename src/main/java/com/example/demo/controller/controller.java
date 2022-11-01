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
		public String getHomePage() {
			return "index";
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
		public String getSignupPage() {
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
		public String registerNewUser(User user, HttpServletRequest request)
				throws UnsupportedEncodingException, MessagingException {
			Service.register(user, getSiteURL(request));
			return "thankyou";
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
	
	@PostMapping("/dashboard")
	public void search(Model model, HttpServletRequest request) {
		String keyword = request.getParameter("keyword");
		List<User> users = Service.search(keyword);
		Integer count = users.size();
		
		model.addAttribute("count", count);
		model.addAttribute("users", users);
		//return "dashboard";
	}
	
	@GetMapping("/forgot-password")
	public String forgotPasswordPage() {
		// show forgot password page, ask user to 
		// enter verified registered email
		return "forgot-password";
	}
	
	@PostMapping("/forgot-password")
	public String processForgotPassword(Model model, HttpServletRequest request) 
			throws UnsupportedEncodingException, MessagingException {
		
		String email = request.getParameter("email");
		
		if(Service.getUserByEmail(email) != null) {
			
			Service.generateResetPasswordToken(email, getSiteURL(request));
			model.addAttribute("error_success", "We have sent you a reset password link to your email. Please check.");
		} else {
			model.addAttribute("error_warning", "Opss!! user not found!");
		}
		
		return "forgot-password";
	}
	
	@GetMapping("/verify-reset-password")
	public String verifyResetPasswordToken(@Param("code") String code) {
		User user = Service.getUserByResetPasswordToken(code);
		// if user exists, means verified user
	    if (user != null) {
	    	Service.resetPasswordToken(code);
	    	String url = "redirect:reset-password?email=" + user.getEmail();
	        return url;
	    } else {
	        return "redirect:verify-fail";
	    }
	}
	@GetMapping("/verify-fail")
	public String verifyFailPage() {
		return "verify_fail";
	}
	
	@GetMapping("/reset-password")
	public String resetPasswordPage(Model model,
			@Param("email") String email) {
		model.addAttribute("email", email);
		return "reset-password";
	}
	
	@PostMapping("/reset-password")
	public String processResetPassword(Model model, HttpServletRequest request) {
		
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		String email = request.getParameter("email");
		
		if(password.equals(cpassword)) { // true, reset password
			System.out.println("email = " + email); // debugging purposes
			Service.updatePassword(email, cpassword);
			
			return "redirect:signin";
			
		} else { // false
			model.addAttribute("error_warning", 
					"hey!! password not match!! Try again.");
			return "reset-password";
		
		}
	}
}

		
	


			



