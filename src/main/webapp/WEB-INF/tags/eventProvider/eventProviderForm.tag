<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>

<%@ attribute name="formMessageText" required="true" type="java.lang.String" %>
<%@ attribute name="isEdit" type="java.lang.Boolean" %>
<%@ attribute name="modelAttrName" required="true" %>
<c:set var="eventProviderRequest" value="${requestScope[modelAttrName]}" />

<div class="form-container">
    <c:if test="${isEdit}">
        <form:input path="id" type="hidden" />
    </c:if>

    <h3>${formMessageText}</h3>
    <hr class="separator">

    <div class="form-block">
        <label><spring:message code="eventProviderDto.tradingName" /></label>
        <form:input path="tradingName" />
        <form:errors path="tradingName" class="form-error" />
    </div>

    <div class="form-block">
        <label><spring:message code="eventProviderDto.companyName" /></label>
        <form:input path="companyName" />
        <form:errors path="companyName" class="form-error" />
    </div>

    <div class="form-block">
        <label><spring:message code="eventProviderDto.registrationNumber" /></label>
        <form:input id="registrationNumber" path="registrationNumber" placeholder="00.000.000/0000-00" maxlength="18" />
        <form:errors path="registrationNumber" class="form-error" />
    </div>

    <div class="form-block">
        <label><spring:message code="eventProviderDto.description" /></label>
        <form:textarea path="description" rows="6" cols="30" />
        <form:errors path="description" class="form-error" />
    </div>

    <h3>Contato</h3>
    <hr class="separator">

    <shared:incrementalField
            label="phone"
            name="phones"
            type="tel"
            limit="3"
            placeholder="(00) 00000-0000"
            list="${eventProviderRequest.phones}"
    />

    <shared:incrementalField
            label="email"
            name="emails"
            type="email"
            limit="3"
            placeholder="exemplo@email.com"
            list="${eventProviderRequest.emails}"
    />

    <h3>Endereço</h3>
    <hr class="separator">

    <div class="form-block">
        <label><spring:message code="eventProviderDto.zipCode" /></label>
        <form:input path="zipCode" />
        <form:errors path="zipCode" class="form-error" />

        <label><spring:message code="eventProviderDto.city" /></label>
        <form:input path="city" />
        <form:errors path="city" class="form-error" />

        <label><spring:message code="eventProviderDto.state" /></label>
        <form:input path="state" />
        <form:errors path="state" class="form-error" />

        <label><spring:message code="eventProviderDto.neighborhood" /></label>
        <form:input path="neighborhood" />
        <form:errors path="neighborhood" class="form-error" />
    </div>

    <div class="form-block">
        <form:button type="submit" class="btn-save">
            Salvar Alterações
        </form:button>
    </div>
</div>
