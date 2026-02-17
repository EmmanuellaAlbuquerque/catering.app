<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="label" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>
<%@ attribute name="placeholder" required="true" %>
<%@ attribute name="list" required="false" type="java.util.Collection" %>
<spring:message var="inputTextContent" code="eventProviderDto.${label}" />

<div class="${name}-container">
    <div class="form-block">
        <label><spring:message code="eventProviderDto.${name}" /></label>

        <div id="${name}-inputs-list">
            <c:choose>
                <c:when test="${not empty list}">
                    <c:forEach items="${list}" var="item" varStatus="status">
                        <div>
                            <label>${inputTextContent} ${status.count}</label>

                            <input type="${type}" name="${name}[${status.index}]" value="${item}" class="${label}-input" placeholder="${placeholder}" />
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div>
                        <label><spring:message code="eventProviderDto.${label}" /> 1</label>
                        <input type="${type}" name="${name}[0]" class="${label}-input" placeholder="${placeholder}" />
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <form:errors path="${name}" class="form-error" />
    </div>

    <button type="button" id="add-${label}">
        + Adicionar outro <spring:message code="eventProviderDto.${label}" />
    </button>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        new IncrementalField('${name}', '${label}', '${inputTextContent}', '${type}', ${limit}, '${placeholder}');
    })
</script>
