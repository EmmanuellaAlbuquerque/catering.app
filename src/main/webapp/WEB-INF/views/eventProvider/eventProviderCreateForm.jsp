<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>
<%@ taglib prefix="eventProvider" tagdir="/WEB-INF/tags/eventProvider" %>

<c:set var="htmlLang" value="${pageContext.response.locale.language eq 'pt' ? 'pt-BR' : pageContext.response.locale.language}" />

<!doctype html>
<html lang="${htmlLang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="eventProvider.create.title" /></title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame">
        <section class="page-topbar">
            <shared:localeSwitcher />
        </section>

        <section class="page-hero">
            <span class="page-kicker"><spring:message code="common.platformName" /></span>
            <h1><spring:message code="eventProvider.create.hero.title" /></h1>
            <p><spring:message code="eventProvider.create.hero.description" /></p>

            <div class="hero-points">
                <div class="hero-point">
                    <strong><spring:message code="eventProvider.create.hero.point1.title" /></strong>
                    <span><spring:message code="eventProvider.create.hero.point1.description" /></span>
                </div>
                <div class="hero-point">
                    <strong><spring:message code="eventProvider.create.hero.point2.title" /></strong>
                    <span><spring:message code="eventProvider.create.hero.point2.description" /></span>
                </div>
                <div class="hero-point">
                    <strong><spring:message code="eventProvider.create.hero.point3.title" /></strong>
                    <span><spring:message code="eventProvider.create.hero.point3.description" /></span>
                </div>
            </div>
        </section>

        <section class="page-content">
            <form:form cssClass="event-form" modelAttribute="eventProviderCreateRequest" method="post" action="/events/create" enctype="multipart/form-data">
                <eventProvider:eventProviderForm modelAttrName="eventProviderCreateRequest" formMessageCode="eventProvider.create.form.title" />
            </form:form>
        </section>
    </main>

    <shared:endScripts />
</body>
</html>
