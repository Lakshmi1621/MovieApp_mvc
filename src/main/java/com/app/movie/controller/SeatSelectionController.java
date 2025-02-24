package com.app.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeatSelectionController {

	@GetMapping("/movieapp/seat-selection")
	public String viewSeats() {
		return "seatSelection";
	}
	@PostMapping("/movieapp/process-selection")
    public String processSelection(
        @RequestParam("selectedDate") String selectedDate,
        @RequestParam("showTime") String showTime,
        HttpSession session
    ) {
        // Store selected date and showtime in the session
        session.setAttribute("selectedDate", selectedDate);
        session.setAttribute("showTime", showTime);

        // Redirect to the actual seat selection page
        return "redirect:/movieapp/seatSelection";
    }
	@GetMapping("/movieapp/seat-selection")
	public String showSeatSelectionPage(HttpSession session, Model model) {
	    // Retrieve selected date and showtime from session
	    String selectedDate = (String) session.getAttribute("selectedDate");
	    String showTime = (String) session.getAttribute("showTime");

	    // Add to model for display
	    model.addAttribute("selectedDate", selectedDate);
	    model.addAttribute("showTime", showTime);

	    return "seatSelection";
	}

	
}

}
