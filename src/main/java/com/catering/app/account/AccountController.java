package com.catering.app.account;

import com.catering.app.account.request.EventProviderAccountCreateRequest;
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

    @GetMapping("/sign-up/event-providers")
    public String createEventProviderAccount(Model model, EventProviderAccountCreateRequest eventProviderAccountCreateRequest) {
        model.addAttribute("eventProviderAccountCreateRequest", eventProviderAccountCreateRequest);
        return "account/eventProviderSignUpForm";
    }

    @PostMapping("/sign-up/event-providers")
    public String createEventProviderAccount(
            @Valid EventProviderAccountCreateRequest eventProviderAccountCreateRequest,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Nao foi possivel criar sua conta. Verifique os erros e tente novamente.");
            return "account/eventProviderSignUpForm";
        }

        try {
            accountService.createEventProviderAccount(eventProviderAccountCreateRequest);
        } catch (DuplicateEmailException ex) {
            bindingResult.rejectValue("email", "account.email.duplicate", ex.getMessage());
            model.addAttribute("message", "Nao foi possivel criar sua conta. Verifique os erros e tente novamente.");
            return "account/eventProviderSignUpForm";
        }

        redirectAttributes.addFlashAttribute("message", "Conta criada com sucesso! Agora cadastre seu fornecedor de eventos.");
        return "redirect:/events/create";
    }
}
