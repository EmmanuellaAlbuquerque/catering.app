<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>

<c:set var="htmlLang" value="${pageContext.response.locale.language eq 'pt' ? 'pt-BR' : pageContext.response.locale.language}" />
<spring:message var="emailPlaceholder" code="account.signup.fields.email.placeholder" />
<spring:message var="passwordPlaceholder" code="account.signup.fields.password.placeholder" />

<!doctype html>
<html lang="${htmlLang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="account.signup.title" /></title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame page-frame--auth">
        <section class="page-topbar">
            <shared:localeSwitcher />
        </section>

        <section class="page-hero">
            <span class="page-kicker"><spring:message code="account.signup.hero.kicker" /></span>
            <h1><spring:message code="account.signup.hero.title" /></h1>
            <p><spring:message code="account.signup.hero.description" /></p>

            <div class="hero-points">
                <div class="hero-point">
                    <strong><spring:message code="account.signup.hero.point1.title" /></strong>
                    <span><spring:message code="account.signup.hero.point1.description" /></span>
                </div>
                <div class="hero-point">
                    <strong><spring:message code="account.signup.hero.point2.title" /></strong>
                    <span><spring:message code="account.signup.hero.point2.description" /></span>
                </div>
            </div>
        </section>

        <section class="page-content">
            <form:form cssClass="event-form" modelAttribute="eventProviderAccountCreateRequest" method="post" action="/accounts/sign-up/event-providers">
                <div class="form-container">
                    <shared:message />

                    <div class="form-shell">
                        <header class="form-header">
                            <div class="form-header-copy">
                                <span class="form-eyebrow"><spring:message code="account.signup.header.eyebrow" /></span>
                                <h2><spring:message code="account.signup.header.title" /></h2>
                                <p><spring:message code="account.signup.header.description" /></p>
                            </div>
                        </header>

                        <section class="section-panel">
                            <div class="section-heading">
                                <span class="section-index">01</span>
                                <div>
                                    <h3><spring:message code="account.signup.section.credentials.title" /></h3>
                                    <p><spring:message code="account.signup.section.credentials.description" /></p>
                                </div>
                            </div>

                            <div class="field-grid">
                                <div class="form-block field-span-full">
                                    <label for="email"><spring:message code="account.signup.fields.email.label" /></label>
                                    <form:input path="email" id="email" type="email" placeholder="${emailPlaceholder}" />
                                    <form:errors path="email" cssClass="form-error" />
                                </div>

                                <div class="form-block field-span-full">
                                    <label for="password"><spring:message code="account.signup.fields.password.label" /></label>
                                    <form:password path="password" id="password" showPassword="true" placeholder="${passwordPlaceholder}" />
                                    <form:errors path="password" cssClass="form-error" />
                                </div>
                            </div>
                        </section>

                        <div class="form-actions">
                            <div class="form-actions-copy">
                                <spring:message code="account.signup.actions.support" />
                            </div>

                            <button class="btn-save" type="submit"><spring:message code="account.signup.actions.submit" /></button>
                        </div>
                    </div>
                </div>
            </form:form>
        </section>
    </main>
</body>
</html>
