<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>

<%@ attribute name="formMessageCode" required="true" type="java.lang.String" %>
<%@ attribute name="formMessageArguments" required="false" type="java.lang.Object" %>
<%@ attribute name="isEdit" type="java.lang.Boolean" %>
<%@ attribute name="modelAttrName" required="true" %>
<c:set var="eventProviderRequest" value="${requestScope[modelAttrName]}" />
<spring:message var="formEyebrow" code="${isEdit ? 'eventProvider.form.header.eyebrow.edit' : 'eventProvider.form.header.eyebrow.create'}" />
<spring:message var="formSupportText" code="${isEdit ? 'eventProvider.form.header.support.edit' : 'eventProvider.form.header.support.create'}" />
<spring:message var="tradingNamePlaceholder" code="eventProvider.form.fields.tradingName.placeholder" />
<spring:message var="companyNamePlaceholder" code="eventProvider.form.fields.companyName.placeholder" />
<spring:message var="registrationNumberPlaceholder" code="eventProvider.form.fields.registrationNumber.placeholder" />
<spring:message var="descriptionPlaceholder" code="eventProvider.form.fields.description.placeholder" />
<spring:message var="zipCodePlaceholder" code="eventProvider.form.fields.zipCode.placeholder" />
<spring:message var="statePlaceholder" code="eventProvider.form.fields.state.placeholder" />
<spring:message var="cityPlaceholder" code="eventProvider.form.fields.city.placeholder" />
<spring:message var="neighborhoodPlaceholder" code="eventProvider.form.fields.neighborhood.placeholder" />
<spring:message var="galleryImageAlt" code="eventProvider.form.gallery.imageAlt" />

<div class="form-container">
    <shared:message />

    <c:if test="${isEdit}">
        <form:input path="id" type="hidden" />
    </c:if>

    <div class="form-shell">
        <div class="form-header">
            <div class="form-header-copy">
                <span class="form-eyebrow">${formEyebrow}</span>
                <h2><spring:message code="${formMessageCode}" arguments="${formMessageArguments}" /></h2>
                <p>${formSupportText}</p>
            </div>
        </div>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">01</span>
                <div>
                    <h3><spring:message code="eventProvider.form.section.profile.title" /></h3>
                    <p><spring:message code="eventProvider.form.section.profile.description" /></p>
                </div>
            </div>

            <div class="field-grid field-grid--dual">
                <div class="form-block">
                    <label><spring:message code="eventProviderDto.tradingName" /></label>
                    <form:input path="tradingName" placeholder="${tradingNamePlaceholder}" />
                    <form:errors path="tradingName" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.companyName" /></label>
                    <form:input path="companyName" placeholder="${companyNamePlaceholder}" />
                    <form:errors path="companyName" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.registrationNumber" /></label>
                    <form:input id="registrationNumber" path="registrationNumber" placeholder="${registrationNumberPlaceholder}" maxlength="18" />
                    <form:errors path="registrationNumber" class="form-error" />
                </div>

                <div class="form-block field-span-full">
                    <label><spring:message code="eventProviderDto.description" /></label>
                    <p class="field-note"><spring:message code="eventProvider.form.description.help" /></p>
                    <form:textarea id="description" path="description" rows="6" cols="30" maxlength="500" placeholder="${descriptionPlaceholder}" />
                    <span id="descriptionCounter" class="field-counter"></span>
                    <form:errors path="description" class="form-error" />
                </div>
            </div>
        </section>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">02</span>
                <div>
                    <h3><spring:message code="eventProvider.form.section.contact.title" /></h3>
                    <p><spring:message code="eventProvider.form.section.contact.description" /></p>
                </div>
            </div>

            <div class="field-grid field-grid--dual">
                <shared:incrementalField
                        label="phone"
                        name="phones"
                        type="tel"
                        limit="3"
                        placeholderCode="eventProvider.form.fields.phone.placeholder"
                        maxlength="15"
                        list="${eventProviderRequest.phones}"
                />

                <shared:incrementalField
                        label="email"
                        name="emails"
                        type="email"
                        limit="3"
                        placeholderCode="eventProvider.form.fields.email.placeholder"
                        list="${eventProviderRequest.emails}"
                />
            </div>
        </section>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">03</span>
                <div>
                    <h3><spring:message code="eventProvider.form.section.address.title" /></h3>
                    <p><spring:message code="eventProvider.form.section.address.description" /></p>
                </div>
            </div>

            <div class="field-grid field-grid--dual">
                <div class="form-block">
                    <label><spring:message code="eventProviderDto.zipCode" /></label>
                    <form:input id="zipCode" path="zipCode" placeholder="${zipCodePlaceholder}" maxlength="9" />
                    <form:errors path="zipCode" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.state" /></label>
                    <form:input id="state" path="state" maxlength="2" placeholder="${statePlaceholder}" />
                    <form:errors path="state" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.city" /></label>
                    <form:input id="city" path="city" placeholder="${cityPlaceholder}" />
                    <form:errors path="city" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.neighborhood" /></label>
                    <form:input id="neighborhood" path="neighborhood" placeholder="${neighborhoodPlaceholder}" />
                    <form:errors path="neighborhood" class="form-error" />
                </div>
            </div>
        </section>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">04</span>
                <div>
                    <h3><spring:message code="eventProvider.form.section.payment.title" /></h3>
                    <p><spring:message code="eventProvider.form.section.payment.description" /></p>
                </div>
            </div>

            <div class="field-grid">
                <div class="form-block field-span-full">
                    <label><spring:message code="eventProviderDto.paymentMethods" /></label>
                    <p class="field-note"><spring:message code="eventProviderDto.paymentMethods.help" /></p>

                    <div class="payment-method-grid">
                        <c:forEach items="${paymentMethodOptions}" var="paymentMethod">
                            <label class="payment-method-option">
                                <input
                                        type="checkbox"
                                        name="paymentMethods"
                                        value="${paymentMethod}"
                                        <c:if test="${eventProviderRequest.paymentMethods != null and eventProviderRequest.paymentMethods.contains(paymentMethod)}">checked="checked"</c:if>
                                />
                                <span class="payment-method-card">
                                    <span class="payment-method-check" aria-hidden="true"></span>
                                    <span class="payment-method-title">
                                        <spring:message code="paymentMethod.${paymentMethod}" />
                                    </span>
                                </span>
                            </label>
                        </c:forEach>
                    </div>

                    <form:errors path="paymentMethods" class="form-error" />
                </div>
            </div>
        </section>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">05</span>
                <div>
                    <h3><spring:message code="eventProvider.form.section.gallery.title" /></h3>
                    <p><spring:message code="eventProvider.form.section.gallery.description" /></p>
                </div>
            </div>

            <div class="field-grid">
                <div class="form-block field-span-full">
                    <label><spring:message code="eventProvider.form.gallery.label" /></label>
                    <div class="upload-area" id="drop-zone-main" data-image-alt="${galleryImageAlt}">
                        <span class="upload-tip"><spring:message code="eventProvider.form.gallery.tip" /></span>
                        <form:input path="images" type="file" name="images" class="upload-input" id="imageInputMain" multiple="multiple" accept="image/*" />
                        <div class="photo-grid" id="gallery-main"></div>
                    </div>
                </div>

                <c:if test="${not empty eventProviderUpdateRequest.urlImages}">
                    <div class="field-span-full">
                        <div class="photo-display-grid">
                            <c:forEach items="${eventProviderUpdateRequest.urlImages}" var="urlImage">
                                <div class="photo-item-box">
                                    <img src="/upload/images/${urlImage}" alt="${galleryImageAlt}">
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
        </section>

        <div class="form-actions">
            <div class="form-actions-copy">
                <spring:message code="eventProvider.form.actions.support" />
            </div>

            <div class="form-block">
                <form:button type="submit" class="btn-save">
                    <spring:message code="common.actions.saveChanges" />
                </form:button>
            </div>
        </div>
    </div>
</div>
