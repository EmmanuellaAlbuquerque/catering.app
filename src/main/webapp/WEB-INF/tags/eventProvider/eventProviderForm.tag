<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>

<%@ attribute name="formMessageText" required="true" type="java.lang.String" %>
<%@ attribute name="isEdit" type="java.lang.Boolean" %>
<%@ attribute name="modelAttrName" required="true" %>
<c:set var="eventProviderRequest" value="${requestScope[modelAttrName]}" />
<c:set var="formEyebrow" value="${isEdit ? 'Cadastro ativo' : 'Novo cadastro'}" />
<c:set var="formSupportText" value="${isEdit ? 'Atualize o perfil do fornecedor com foco em legibilidade e padronizacao dos dados.' : 'Monte um perfil completo para o fornecedor aparecer de forma clara e confiavel na plataforma.'}" />
<c:set var="formBadgeText" value="${isEdit ? 'Modo edicao' : 'Pronto para publicar'}" />

<div class="form-container">
    <shared:message />

    <c:if test="${isEdit}">
        <form:input path="id" type="hidden" />
    </c:if>

    <div class="form-shell">
        <div class="form-header">
            <div class="form-header-copy">
                <span class="form-eyebrow">${formEyebrow}</span>
                <h2>${formMessageText}</h2>
                <p>${formSupportText}</p>
            </div>

            <div class="form-badge">${formBadgeText}</div>
        </div>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">01</span>
                <div>
                    <h3>Perfil do fornecedor</h3>
                    <p>Defina como este parceiro vai ser reconhecido dentro da plataforma e pelos clientes.</p>
                </div>
            </div>

            <div class="field-grid field-grid--dual">
                <div class="form-block">
                    <label><spring:message code="eventProviderDto.tradingName" /></label>
                    <form:input path="tradingName" placeholder="Ex.: Atelier Gourmet" />
                    <form:errors path="tradingName" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.companyName" /></label>
                    <form:input path="companyName" placeholder="Nome juridico do fornecedor" />
                    <form:errors path="companyName" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.registrationNumber" /></label>
                    <form:input id="registrationNumber" path="registrationNumber" placeholder="00.000.000/0000-00" maxlength="18" />
                    <form:errors path="registrationNumber" class="form-error" />
                </div>

                <div class="form-block field-span-full">
                    <label><spring:message code="eventProviderDto.description" /></label>
                    <p class="field-note">Descreva estilo de atendimento, diferenciais e contexto de uso do servico.</p>
                    <form:textarea path="description" rows="6" cols="30" placeholder="Conte como esse fornecedor atende eventos e o que torna a experiencia mais especial." />
                    <form:errors path="description" class="form-error" />
                </div>
            </div>
        </section>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">02</span>
                <div>
                    <h3>Contato</h3>
                    <p>Organize os principais canais de atendimento com boa leitura e padronizacao.</p>
                </div>
            </div>

            <div class="field-grid field-grid--dual">
                <shared:incrementalField
                        label="phone"
                        name="phones"
                        type="tel"
                        limit="3"
                        placeholder="(00) 00000-0000"
                        maxlength="15"
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
            </div>
        </section>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">03</span>
                <div>
                    <h3>Endereco</h3>
                    <p>Use o CEP para completar os dados principais e manter os cadastros consistentes.</p>
                </div>
            </div>

            <div class="field-grid field-grid--dual">
                <div class="form-block">
                    <label><spring:message code="eventProviderDto.zipCode" /></label>
                    <p class="field-note">Ao digitar o CEP, bairro, cidade e estado podem ser preenchidos automaticamente.</p>
                    <form:input id="zipCode" path="zipCode" placeholder="00000-000" maxlength="9" />
                    <form:errors path="zipCode" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.state" /></label>
                    <form:input id="state" path="state" maxlength="2" placeholder="UF" />
                    <form:errors path="state" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.city" /></label>
                    <form:input id="city" path="city" placeholder="Cidade do atendimento" />
                    <form:errors path="city" class="form-error" />
                </div>

                <div class="form-block">
                    <label><spring:message code="eventProviderDto.neighborhood" /></label>
                    <form:input id="neighborhood" path="neighborhood" placeholder="Bairro" />
                    <form:errors path="neighborhood" class="form-error" />
                </div>
            </div>
        </section>

        <section class="section-panel">
            <div class="section-heading">
                <span class="section-index">04</span>
                <div>
                    <h3>Galeria visual</h3>
                    <p>Apresente imagens que ajudem o cliente a confiar na experiencia do fornecedor.</p>
                </div>
            </div>

            <div class="field-grid">
                <div class="form-block field-span-full">
                    <label>Fotos do local / evento</label>
                    <div class="upload-area" id="drop-zone-main">
                        <span class="upload-tip">Arraste imagens ou clique para selecionar arquivos que representem o fornecedor.</span>
                        <form:input path="images" type="file" name="images" class="upload-input" id="imageInputMain" multiple="multiple" accept="image/*" />
                        <div class="photo-grid" id="gallery-main"></div>
                    </div>
                </div>

                <c:if test="${not empty eventProviderUpdateRequest.urlImages}">
                    <div class="field-span-full">
                        <div class="photo-display-grid">
                            <c:forEach items="${eventProviderUpdateRequest.urlImages}" var="urlImage">
                                <div class="photo-item-box">
                                    <img src="/upload/images/${urlImage}" alt="Imagem do Evento">
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:if>
            </div>
        </section>

        <div class="form-actions">
            <div class="form-actions-copy">
                Revise os dados antes de salvar. Informacoes padronizadas melhoram a leitura do catalogo e reduzem retrabalho operacional.
            </div>

            <div class="form-block">
                <form:button type="submit" class="btn-save">
                    Salvar alteracoes
                </form:button>
            </div>
        </div>
    </div>
</div>
