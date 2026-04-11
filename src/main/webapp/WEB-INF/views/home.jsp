<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>

<c:set var="htmlLang" value="${pageContext.response.locale.language eq 'pt' ? 'pt-BR' : pageContext.response.locale.language}" />

<!doctype html>
<html lang="${htmlLang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="home.title" /></title>
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body class="event-page">
    <main class="page-frame page-frame--single">
        <section class="page-topbar">
            <shared:localeSwitcher />
        </section>

        <section class="page-hero">
            <span class="page-kicker"><spring:message code="common.platformName" /></span>
            <h1><spring:message code="home.hero.title" /></h1>
            <p><spring:message code="home.hero.description" /></p>
        </section>

        <section class="page-content">
            <div class="form-shell quick-actions-shell">
                <header class="form-header">
                    <div class="form-header-copy">
                        <span class="form-eyebrow"><spring:message code="home.card.eyebrow" /></span>
                        <h2><spring:message code="common.platformName" /></h2>
                        <p><spring:message code="home.card.description" /></p>
                    </div>
                </header>

                <div class="quick-actions">
                    <a class="btn-secondary" href="/accounts/login"><spring:message code="home.actions.login" /></a>
                    <a class="btn-save" href="/accounts/sign-up/event-providers"><spring:message code="home.actions.signUp" /></a>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
