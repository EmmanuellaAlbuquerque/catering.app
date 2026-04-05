<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame page-frame--auth">
        <section class="page-hero">
            <span class="page-kicker">Acesso</span>
            <h1>Entre na sua conta.</h1>
            <p>Use seu e-mail e sua senha para continuar no painel do fornecedor.</p>
        </section>

        <section class="page-content">
            <form:form cssClass="event-form" modelAttribute="accountLoginRequest" method="post" action="/accounts/login">
                <div class="form-container">
                    <c:if test="${not empty message}">
                        <div class="message-toast-container">${message}</div>
                    </c:if>

                    <div class="form-shell">
                        <header class="form-header">
                            <div class="form-header-copy">
                                <span class="form-eyebrow">Login</span>
                                <h2>Acesso do fornecedor</h2>
                            </div>
                        </header>

                        <section class="section-panel">
                            <div class="field-grid">
                                <div class="form-block field-span-full">
                                    <label for="email">E-mail</label>
                                    <form:input path="email" id="email" type="email" placeholder="fornecedor@email.com" />
                                    <form:errors path="email" cssClass="form-error" />
                                </div>

                                <div class="form-block field-span-full">
                                    <label for="password">Senha</label>
                                    <form:password path="password" id="password" showPassword="true" placeholder="Sua senha" />
                                    <form:errors path="password" cssClass="form-error" />
                                </div>
                            </div>
                        </section>

                        <div class="form-actions">
                            <div class="form-actions-copy">
                                Entre para acessar o dashboard e continuar a configuracao do seu fornecedor.
                            </div>

                            <button class="btn-save" type="submit">Entrar</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </section>
    </main>
</body>
</html>
