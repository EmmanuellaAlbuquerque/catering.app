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
    <title>Formulário de Cadastro do Fornecedor de Eventos</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <form:form modelAttribute="eventProviderUpdateRequest" method="post" action="/events/edit" enctype="multipart/form-data">
        <eventProvider:eventProviderForm modelAttrName="eventProviderUpdateRequest" isEdit="true" formMessageText="Dados do Fornecedor do Evento (${eventProviderUpdateRequest.id})"/>
    </form:form>

    <shared:endScripts />
</body>
</html>