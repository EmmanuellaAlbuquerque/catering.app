package com.catering.app.common.config.web;

import com.catering.app.account.AccountOnboardingService;
import com.catering.app.account.AccountSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class OnboardingRedirectInterceptor implements HandlerInterceptor {

    private final AccountOnboardingService accountOnboardingService;

    public OnboardingRedirectInterceptor(AccountOnboardingService accountOnboardingService) {
        this.accountOnboardingService = accountOnboardingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return true;
        }

        Long accountId = (Long) session.getAttribute(AccountSession.ACCOUNT_ID);
        if (!accountOnboardingService.requiresEventProviderRegistration(accountId)) {
            return true;
        }

        String requestUri = request.getRequestURI();
        if (isAllowedDuringOnboarding(requestUri)) {
            return true;
        }

        response.sendRedirect("/events/create");
        return false;
    }

    private boolean isAllowedDuringOnboarding(String requestUri) {
        return requestUri.equals("/events/create")
                || requestUri.equals("/accounts/sign-out")
                || requestUri.startsWith("/accounts/sign-up/event-providers")
                || requestUri.startsWith("/css/")
                || requestUri.startsWith("/js/")
                || requestUri.startsWith("/uploads/");
    }
}
