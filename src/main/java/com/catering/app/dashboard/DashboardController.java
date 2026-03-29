package com.catering.app.dashboard;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.catering.app.account.AccountSession.ACCOUNT_ID;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (session.getAttribute(ACCOUNT_ID) == null) {
            return "redirect:/";
        }

        model.addAttribute("message", "Painel do fornecedor");
        return "dashboard";
    }
}
