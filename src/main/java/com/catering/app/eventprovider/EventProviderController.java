package com.catering.app.eventprovider;

import com.catering.app.eventprovider.request.EventProviderCreateRequest;
import com.catering.app.eventprovider.request.EventProviderUpdateRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/events")
public class EventProviderController {

    private final EventProviderService eventProviderService;

    public EventProviderController(EventProviderService eventProviderService) {
        this.eventProviderService = eventProviderService;
    }

    @GetMapping("/create")
    public String createEventProvider(Model model, EventProviderCreateRequest eventProviderCreateRequest) {

        model.addAttribute("eventProviderCreateDto", eventProviderCreateRequest);

        return "eventProvider/eventProviderCreateForm";
    }

    @PostMapping("/create")
    public String createEventProvider(@Valid EventProviderCreateRequest eventProviderCreateRequest, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Não foi possível salvar as alterações. Verifique os erros e tente novamente.");
            return "eventProvider/eventProviderCreateForm";
        }

        Long savedId = eventProviderService.create(eventProviderCreateRequest);

        model.addAttribute("eventProviderCreateDto", eventProviderCreateRequest);

        redirectAttributes.addFlashAttribute("message", "Criado com sucesso!");

        return "redirect:/events/edit/" + savedId;
    }

    @GetMapping("/edit/{id}")
    public String editEventProvider(Model model, @PathVariable Long id) {

        EventProviderUpdateRequest eventProviderUpdateRequest = eventProviderService.findById(id);

        model.addAttribute("eventProviderUpdateRequest", eventProviderUpdateRequest);

        return "eventProvider/eventProviderEditForm";
    }

    @PostMapping("/edit")
    public String editEventProvider(@Valid EventProviderUpdateRequest eventProviderUpdateRequest, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Não foi possível salvar as alterações. Verifique os erros e tente novamente.");
            return "eventProvider/eventProviderEditForm";
        }

        eventProviderService.update(eventProviderUpdateRequest);

        model.addAttribute("eventProviderUpdateRequest", eventProviderUpdateRequest);

        redirectAttributes.addFlashAttribute("message", "Atualizado com sucesso!");

        return "redirect:/events/edit/" + eventProviderUpdateRequest.getId();
    }
}
