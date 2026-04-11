<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="htmlLang" value="${pageContext.response.locale.language eq 'pt' ? 'pt-BR' : pageContext.response.locale.language}" />

<!doctype html>
<html lang="${htmlLang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="dashboard.title" /></title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame page-frame--single">
        <section class="page-topbar">
            <div class="page-topbar-actions">
                <shared:localeSwitcher />
                <form method="post" action="/accounts/sign-out">
                    <button class="btn-ghost" type="submit"><spring:message code="dashboard.topbar.signOut" /></button>
                </form>
            </div>
        </section>

        <section class="page-hero">
            <span class="page-kicker"><spring:message code="dashboard.hero.kicker" /></span>
            <h1><spring:message code="dashboard.hero.title" /></h1>
        </section>

        <section class="page-content">
            <div class="form-shell quick-actions-shell">
                <header class="form-header">
                    <div class="form-header-copy">
                        <span class="form-eyebrow"><spring:message code="dashboard.header.eyebrow" /></span>
                        <h2><spring:message code="dashboard.header.title" /></h2>
                    </div>
                </header>
            </div>
        </section>
    </main>
</body>
</html>
