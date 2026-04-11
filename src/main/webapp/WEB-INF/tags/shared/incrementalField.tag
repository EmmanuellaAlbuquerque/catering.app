<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="label" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="type" required="true" %>
<%@ attribute name="limit" required="true" type="java.lang.Integer" %>
<%@ attribute name="placeholderCode" required="true" %>
<%@ attribute name="list" required="false" type="java.util.Collection" %>
<%@ attribute name="maxlength" required="false" type="java.lang.Integer" %>
<spring:message var="placeholderText" code="${placeholderCode}" />
<spring:message var="inputTextContent" code="eventProviderDto.${label}" />
<spring:message var="removeButtonText" code="common.actions.remove" />

<div class="${name}-container">
    <div class="form-block">
        <label><spring:message code="eventProviderDto.${name}" /></label>

        <div id="${name}-inputs-list">
            <c:choose>
                <c:when test="${not empty list}">
                    <c:forEach items="${list}" var="item" varStatus="status">
                        <div class="input-group-dynamic">
                            <label>${inputTextContent} ${status.count}</label>
                            <input type="${type}" name="${name}[${status.index}]" value="${item}" class="${label}-input" placeholder="${placeholderText}" maxlength="${maxlength}" />
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="input-group-dynamic">
                        <label><spring:message code="eventProviderDto.${label}" /> 1</label>
                        <input type="${type}" name="${name}[0]" class="${label}-input" placeholder="${placeholderText}" maxlength="${maxlength}" />
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <form:errors path="${name}" class="form-error" />
    </div>

    <button type="button" id="add-${label}" class="btn-add">
        <spring:message code="eventProvider.form.actions.addAnother" arguments="${inputTextContent}" />
    </button>
</div>

<script>
    document.addEventListener("DOMContentLoaded", () => {
        new IncrementalField('${name}', '${label}', '${inputTextContent}', '${type}', ${limit}, '${placeholderText}', '${removeButtonText}', ${empty maxlength ? 'null' : maxlength});
    })
</script>
