<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>

<c:if test="${not empty messageCode or not empty message}">
    <div class="message-toast-container">
        <c:choose>
            <c:when test="${not empty messageCode}">
                <spring:message code="${messageCode}" />
            </c:when>
            <c:otherwise>
                ${message}
            </c:otherwise>
        </c:choose>
    </div>
</c:if>
