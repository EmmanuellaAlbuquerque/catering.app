package com.catering.app.account;

import com.catering.app.account.request.AccountLoginRequest;
import com.catering.app.account.request.EventProviderAccountCreateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String login(Model model, AccountLoginRequest accountLoginRequest, HttpSession session) {
        if (session.getAttribute(AccountSession.ACCOUNT_ID) != null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("accountLoginRequest", accountLoginRequest);
        return "account/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Valid AccountLoginRequest accountLoginRequest,
            BindingResult bindingResult,
            Model model,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("messageCode", "account.login.feedback.invalidForm");
            return "account/loginForm";
        }

        try {
            Long accountId = accountService.authenticate(accountLoginRequest);
            session.setAttribute(AccountSession.ACCOUNT_ID, accountId);
            return "redirect:/dashboard";
        } catch (InvalidCredentialsException ex) {
            model.addAttribute("messageCode", "account.login.feedback.invalidCredentials");
            return "account/loginForm";
        }
    }

    @GetMapping("/sign-up/event-providers")
    public String createEventProviderAccount(Model model, EventProviderAccountCreateRequest eventProviderAccountCreateRequest, HttpSession session) {
        if (session.getAttribute(AccountSession.ACCOUNT_ID) != null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("eventProviderAccountCreateRequest", eventProviderAccountCreateRequest);
        return "account/eventProviderSignUpForm";
    }

    @PostMapping("/sign-up/event-providers")
    public String createEventProviderAccount(
            @Valid EventProviderAccountCreateRequest eventProviderAccountCreateRequest,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("messageCode", "account.signup.feedback.invalidForm");
            return "account/eventProviderSignUpForm";
        }

        try {
            Long accountId = accountService.createEventProviderAccount(eventProviderAccountCreateRequest);
            session.setAttribute(AccountSession.ACCOUNT_ID, accountId);
        } catch (DuplicateEmailException ex) {
            bindingResult.rejectValue("email", "account.signup.feedback.duplicateEmail");
            model.addAttribute("messageCode", "account.signup.feedback.invalidForm");
            return "account/eventProviderSignUpForm";
        }

        redirectAttributes.addFlashAttribute("messageCode", "account.signup.feedback.success");
        return "redirect:/events/create";
    }

    @PostMapping("/sign-out")
    public String signOut(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
