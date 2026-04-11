package com.catering.app.eventprovider;

import com.catering.app.eventprovider.domain.PaymentMethod;
import com.catering.app.eventprovider.request.EventProviderCreateRequest;
import com.catering.app.eventprovider.request.EventProviderUpdateRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

import static com.catering.app.account.AccountSession.ACCOUNT_ID;

@Controller
@RequestMapping("/events")
public class EventProviderController {

    private final EventProviderService eventProviderService;

    public EventProviderController(EventProviderService eventProviderService) {
        this.eventProviderService = eventProviderService;
    }

    @GetMapping("/create")
    public String createEventProvider(Model model, EventProviderCreateRequest eventProviderCreateRequest, HttpSession session) {
        Long accountId = (Long) session.getAttribute(ACCOUNT_ID);
        if (eventProviderService.hasEventProviderForAccount(accountId)) {
            EventProviderUpdateRequest eventProviderUpdateRequest = eventProviderService.findByOwnerAccountId(accountId);
            return "redirect:/events/edit/" + eventProviderUpdateRequest.getId();
        }

        model.addAttribute("eventProviderCreateDto", eventProviderCreateRequest);
        model.addAttribute("paymentMethodOptions", PaymentMethod.values());
        return "eventProvider/eventProviderCreateForm";
    }

    @PostMapping("/create")
    public String createEventProvider(
            @Valid EventProviderCreateRequest eventProviderCreateRequest,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("messageCode", "eventProvider.feedback.invalidForm");
            model.addAttribute("paymentMethodOptions", PaymentMethod.values());
            return "eventProvider/eventProviderCreateForm";
        }

        Long accountId = (Long) session.getAttribute(ACCOUNT_ID);
        Long savedId = eventProviderService.create(eventProviderCreateRequest, accountId);

        model.addAttribute("eventProviderCreateDto", eventProviderCreateRequest);
        redirectAttributes.addFlashAttribute("messageCode", "eventProvider.create.feedback.success");

        return "redirect:/events/edit/" + savedId;
    }

    @GetMapping("/edit/{id}")
    public String editEventProvider(Model model, @PathVariable Long id, HttpSession session) {
        Optional<String> redirectPath = resolveUnauthorizedEditRedirect(session, id);
        if (redirectPath.isPresent()) {
            return redirectPath.get();
        }

        EventProviderUpdateRequest eventProviderUpdateRequest = eventProviderService.findById(id);
        model.addAttribute("eventProviderUpdateRequest", eventProviderUpdateRequest);
        model.addAttribute("paymentMethodOptions", PaymentMethod.values());
        return "eventProvider/eventProviderEditForm";
    }

    @PostMapping("/edit")
    public String editEventProvider(
            @Valid EventProviderUpdateRequest eventProviderUpdateRequest,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes,
            HttpSession session
    ) {
        Optional<String> redirectPath = resolveUnauthorizedEditRedirect(session, eventProviderUpdateRequest.getId());
        if (redirectPath.isPresent()) {
            return redirectPath.get();
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("messageCode", "eventProvider.feedback.invalidForm");
            model.addAttribute("paymentMethodOptions", PaymentMethod.values());
            return "eventProvider/eventProviderEditForm";
        }

        eventProviderService.update(eventProviderUpdateRequest);

        model.addAttribute("eventProviderUpdateRequest", eventProviderUpdateRequest);
        redirectAttributes.addFlashAttribute("messageCode", "eventProvider.edit.feedback.success");

        return "redirect:/events/edit/" + eventProviderUpdateRequest.getId();
    }

    private Optional<String> resolveUnauthorizedEditRedirect(HttpSession session, Long eventProviderId) {
        Long accountId = (Long) session.getAttribute(ACCOUNT_ID);

        if (accountId == null) {
            return Optional.of("redirect:/accounts/sign-up/event-providers");
        }

        if (eventProviderService.ownsEventProvider(accountId, eventProviderId)) {
            return Optional.empty();
        }

        if (eventProviderService.hasEventProviderForAccount(accountId)) {
            EventProviderUpdateRequest eventProviderUpdateRequest = eventProviderService.findByOwnerAccountId(accountId);
            return Optional.of("redirect:/events/edit/" + eventProviderUpdateRequest.getId());
        }

        return Optional.of("redirect:/events/create");
    }
}
