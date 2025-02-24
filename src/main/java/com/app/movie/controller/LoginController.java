package com.app.movie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.movie.entity.UserEntity;
import com.app.movie.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/movieapp")
public class LoginController {

	@Autowired
	private AuthService authService;

	@GetMapping("/login")
	public String login() {
		return "index";
	}

	@PostMapping("/auth")
	public void login(@RequestParam("phone") String phone, @RequestParam("password") String password,
			HttpServletResponse response) throws Exception {

		UserEntity loggedIn = authService.authenticate(phone, password);

		if (loggedIn != null) {
			response.sendRedirect("/movieapp/home");
		} else {
			response.sendRedirect("/movieapp/login");
		}

	}
	@GetMapping("/movieapp/login")
    public String showLoginPage() {
        return "index"; // Renders login.jsp
    }

	@PostMapping("/movieapp/login")
    public String  loginUser(
        @RequestParam String email,
        @RequestParam String password,
        HttpSession session,
        Model model
    ) {
        // Call findByEmail() on the injected instance
        UserEntity user = userRepository.findByEmail(email);
        // ... rest of the code
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("cu", user);
            return "redirect:/movieapp/profile";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "index";
        }
    }
}


}
