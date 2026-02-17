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
    <h1>Cadastrar Fornecedor de Eventos</h1>

    <shared:message />

    <form:form modelAttribute="eventProviderCreateRequest" method="post" action="/events/create">
        <eventProvider:eventProviderForm modelAttrName="eventProviderCreateRequest" />
    </form:form>

    <script src="/js/formUtils/IncrementalField.js" type="text/javascript"></script>
    <script src="/js/formUtils/CnpjInputMask.js" type="text/javascript"></script>

    <script>
        document.addEventListener('DOMContentLoaded', () => {
            new CnpjInputMask('registrationNumber');
        });
    </script>
</body>
</html>