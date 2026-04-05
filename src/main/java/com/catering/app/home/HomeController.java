package com.catering.app.home;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.catering.app.account.AccountSession.ACCOUNT_ID;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute(ACCOUNT_ID) != null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("message", "Catering Platform");
        return "home";
    }
}
