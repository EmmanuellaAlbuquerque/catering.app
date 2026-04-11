<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ tag language="java" pageEncoding="UTF-8"%>

<div class="locale-switcher-wrapper">
    <c:set var="localeSwitcherAction"
           value="${requestScope['jakarta.servlet.forward.request_uri'] ne null ? requestScope['jakarta.servlet.forward.request_uri'] : pageContext.request.requestURI}" />

    <form method="get" action="${localeSwitcherAction}" class="locale-switcher">
        <c:forEach items="${pageContext.request.parameterMap}" var="parameter">
            <c:if test="${parameter.key ne 'lang'}">
                <input type="hidden" name="${parameter.key}" value="${parameter.value[0]}" />
            </c:if>
        </c:forEach>

        <span class="locale-switcher-label">
            <spring:message code="locale.switcher.label" />
        </span>

        <button
                type="submit"
                class="locale-switcher-button ${pageContext.response.locale.language eq 'pt' ? 'is-active' : ''}"
                name="lang"
                value="pt-BR"
        >
            PT-BR
        </button>
        <button
                type="submit"
                class="locale-switcher-button ${pageContext.response.locale.language eq 'en' ? 'is-active' : ''}"
                name="lang"
                value="en"
        >
            EN
        </button>
        <button
                type="submit"
                class="locale-switcher-button ${pageContext.response.locale.language eq 'es' ? 'is-active' : ''}"
                name="lang"
                value="es"
        >
            ES
        </button>
    </form>
</div>
