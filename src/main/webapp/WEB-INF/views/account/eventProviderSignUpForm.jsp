<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Criar conta de fornecedor de eventos</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame page-frame--auth">
        <section class="page-hero">
            <span class="page-kicker">Conta de acesso</span>
            <h1>Crie sua conta como fornecedor de eventos.</h1>
            <p>Informe seu e-mail e sua senha para criar o acesso inicial e seguir para o cadastro do seu perfil na plataforma.</p>

            <div class="hero-points">
                <div class="hero-point">
                    <strong>Cadastro inicial rapido</strong>
                    <span>Voce cria sua conta com poucos dados e continua o preenchimento do seu perfil em seguida.</span>
                </div>
                <div class="hero-point">
                    <strong>Acesso centralizado</strong>
                    <span>Seu e-mail e sua senha ficam vinculados ao seu acesso na plataforma de forma simples e direta.</span>
                </div>
            </div>
        </section>

        <section class="page-content">
            <form:form cssClass="event-form" modelAttribute="eventProviderAccountCreateRequest" method="post" action="/accounts/sign-up/event-providers">
                <div class="form-container">
                    <c:if test="${not empty message}">
                        <div class="message-toast-container">${message}</div>
                    </c:if>

                    <div class="form-shell">
                        <header class="form-header">
                            <div class="form-header-copy">
                                <span class="form-eyebrow">Cadastro inicial</span>
                                <h2>Acesso do fornecedor de eventos</h2>
                                <p>Use um e-mail valido para sua conta principal. Depois da criacao, voce sera direcionado para cadastrar seu perfil.</p>
                            </div>
                        </header>

                        <section class="section-panel">
                            <div class="section-heading">
                                <span class="section-index">01</span>
                                <div>
                                    <h3>Credenciais</h3>
                                    <p>Esses dados serao usados para identificar seu acesso como fornecedor de eventos na plataforma.</p>
                                </div>
                            </div>

                            <div class="field-grid">
                                <div class="form-block field-span-full">
                                    <label for="email">E-mail</label>
                                    <form:input path="email" id="email" type="email" placeholder="dono@seubuffet.com.br" />
                                    <form:errors path="email" cssClass="form-error" />
                                </div>

                                <div class="form-block field-span-full">
                                    <label for="password">Senha</label>
                                    <form:password path="password" id="password" showPassword="true" placeholder="Minimo de 8 caracteres" />
                                    <form:errors path="password" cssClass="form-error" />
                                </div>
                            </div>
                        </section>

                        <div class="form-actions">
                            <div class="form-actions-copy">
                                Ao continuar, sua conta sera criada e voce podera seguir para o cadastro do seu perfil de fornecedor.
                            </div>

                            <button class="btn-save" type="submit">Criar conta</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </section>
    </main>
</body>
</html>
