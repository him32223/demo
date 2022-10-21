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
			model.addAttribute("loggedinuser", loggedInUser);
			
			return "dashboard";
		}
		
		@GetMapping("/profile")
		public String profilePage(Model model, @RequestParam("id") Integer id) {
			User user = Service.getUserById(id);
			model.addAttribute("user", user);
			return "profile";
		}

    // post method to process registration
@PostMapping("/process_signup")
public String registerUser(Model model, @ModelAttribute("user") User user, HttpServletRequest request)
		throws UnsupportedEncodingException, MessagingException
{	
	Service.register(user, getSiteURL(request)); // we dont have any checking if user exist for now, but later
	return "thankyou";
			
}

// post method to process registration
	@PostMapping("/update-profile")
	public String UpdateUser(Model model, @ModelAttribute("user") User user){	
		Service.updateUserProfile(user); // we dont have any checking if user exist for now, but later
		return "redirect:dashboard";
				
	}

private String getSiteURL(HttpServletRequest request) {
    String siteURL = request.getRequestURL().toString();
    return siteURL.replace(request.getServletPath(), "");
}  
 
@GetMapping("/verify")
public String verifyUser(@Param("code") String code) {
    if (Service.verify(code)) {
        return "verify_success";
    } else {
    	
    }
        return "verify_fail";
}
}
    
