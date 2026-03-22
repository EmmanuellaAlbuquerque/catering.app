<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="shared" tagdir="/WEB-INF/tags/shared" %>
<%@ taglib prefix="eventProvider" tagdir="/WEB-INF/tags/eventProvider" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Formulario de Cadastro do Fornecedor de Eventos</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame">
        <section class="page-hero">
            <span class="page-kicker">Catering Platform</span>
            <h1>Atualize fornecedores com mais clareza e controle.</h1>
            <p>Revise dados, contatos e imagens em uma tela mais clara para manter o cadastro sempre completo e consistente.</p>

            <div class="hero-points">
                <div class="hero-point">
                    <strong>Edição mais simples</strong>
                    <span>Os dados foram organizados em seções para facilitar ajustes no dia a dia.</span>
                </div>
                <div class="hero-point">
                    <strong>Informações mais consistentes</strong>
                    <span>O formulário ajuda a manter contato e endereço preenchidos de forma mais padronizada.</span>
                </div>
                <div class="hero-point">
                    <strong>Galeria mais clara</strong>
                    <span>As fotos atuais e os novos uploads ficam mais visíveis e fáceis de revisar.</span>
                </div>
            </div>
        </section>

        <section class="page-content">
            <form:form cssClass="event-form" modelAttribute="eventProviderUpdateRequest" method="post" action="/events/edit" enctype="multipart/form-data">
                <eventProvider:eventProviderForm modelAttrName="eventProviderUpdateRequest" isEdit="true" formMessageText="Dados do fornecedor (${eventProviderUpdateRequest.id})" />
            </form:form>
        </section>
    </main>

    <shared:endScripts />
</body>
</html>
