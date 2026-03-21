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
            <h1>Cadastre fornecedores com curadoria visual e clareza operacional.</h1>
            <p>Estruture identidade, contatos, endereco e imagens em uma interface mais confiavel para quem vai contratar servicos de catering para eventos.</p>

            <div class="hero-points">
                <div class="hero-point">
                    <strong>Fluxo guiado</strong>
                    <span>As informacoes principais aparecem em blocos claros e em uma sequencia natural.</span>
                </div>
                <div class="hero-point">
                    <strong>Enderecos mais rapidos</strong>
                    <span>O CEP preenche bairro, cidade e estado para reduzir erro manual.</span>
                </div>
                <div class="hero-point">
                    <strong>Apresentacao melhor</strong>
                    <span>O cadastro ja nasce preparado para destacar fotos e transmitir mais confianca.</span>
                </div>
            </div>
        </section>

        <section class="page-content">
            <form:form cssClass="event-form" modelAttribute="eventProviderCreateRequest" method="post" action="/events/create" enctype="multipart/form-data">
                <eventProvider:eventProviderForm modelAttrName="eventProviderCreateRequest" formMessageText="Novo fornecedor" />
            </form:form>
        </section>
    </main>

    <shared:endScripts />
</body>
</html>
