<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>

<c:set var="htmlLang" value="${pageContext.response.locale.language eq 'pt' ? 'pt-BR' : pageContext.response.locale.language}" />
<spring:message var="emailPlaceholder" code="account.login.fields.email.placeholder" />
<spring:message var="passwordPlaceholder" code="account.login.fields.password.placeholder" />

<!doctype html>
<html lang="${htmlLang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="account.login.title" /></title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame page-frame--auth">
        <section class="page-topbar">
            <shared:localeSwitcher />
        </section>

        <section class="page-hero">
            <span class="page-kicker"><spring:message code="account.login.hero.kicker" /></span>
            <h1><spring:message code="account.login.hero.title" /></h1>
            <p><spring:message code="account.login.hero.description" /></p>
        </section>

        <section class="page-content">
            <form:form cssClass="event-form" modelAttribute="accountLoginRequest" method="post" action="/accounts/login">
                <div class="form-container">
                    <shared:message />

                    <div class="form-shell">
                        <header class="form-header">
                            <div class="form-header-copy">
                                <span class="form-eyebrow"><spring:message code="account.login.header.eyebrow" /></span>
                                <h2><spring:message code="account.login.header.title" /></h2>
                            </div>
                        </header>

                        <section class="section-panel">
                            <div class="field-grid">
                                <div class="form-block field-span-full">
                                    <label for="email"><spring:message code="account.login.fields.email.label" /></label>
                                    <form:input path="email" id="email" type="email" placeholder="${emailPlaceholder}" />
                                    <form:errors path="email" cssClass="form-error" />
                                </div>

                                <div class="form-block field-span-full">
                                    <label for="password"><spring:message code="account.login.fields.password.label" /></label>
                                    <form:password path="password" id="password" showPassword="true" placeholder="${passwordPlaceholder}" />
                                    <form:errors path="password" cssClass="form-error" />
                                </div>
                            </div>
                        </section>

                        <div class="form-actions">
                            <div class="form-actions-copy">
                                <spring:message code="account.login.actions.support" />
                            </div>

                            <button class="btn-save" type="submit"><spring:message code="account.login.actions.submit" /></button>
                        </div>
                    </div>
                </div>
            </form:form>
        </section>
    </main>
</body>
</html>
