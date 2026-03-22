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
            <h1>Cadastre fornecedores com mais clareza e organização.</h1>
            <p>Estruture identidade, contatos, endereço e imagens em uma interface mais confiável para quem vai contratar serviços de catering para eventos.</p>

            <div class="hero-points">
                <div class="hero-point">
                    <strong>Cadastro mais organizado</strong>
                    <span>As informações principais ficam distribuídas em blocos claros e fáceis de revisar.</span>
                </div>
                <div class="hero-point">
                    <strong>Dados bem estruturados</strong>
                    <span>Contato, endereço e apresentação visual ficam reunidos no mesmo fluxo de cadastro.</span>
                </div>
                <div class="hero-point">
                    <strong>Apresentação mais consistente</strong>
                    <span>O formulário ajuda a manter o perfil do fornecedor mais completo e mais fácil de entender.</span>
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
