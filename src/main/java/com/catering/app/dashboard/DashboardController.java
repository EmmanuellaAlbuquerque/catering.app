package com.catering.app.dashboard;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.catering.app.account.AccountSession.ACCOUNT_ID;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute(ACCOUNT_ID) == null) {
            return "redirect:/";
        }

        return "dashboard";
    }
}
