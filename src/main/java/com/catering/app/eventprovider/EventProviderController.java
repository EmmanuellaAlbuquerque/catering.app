package com.catering.app.eventprovider;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventProviderController {

    @GetMapping
    public String createEventProvider() {
        return "";
    }
}
