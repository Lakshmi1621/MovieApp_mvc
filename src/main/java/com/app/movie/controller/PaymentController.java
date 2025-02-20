package com.app.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaymentController {

	@GetMapping("/movieapp/payments")
	public String viewPayments() {
		return "payments";
	}
}
