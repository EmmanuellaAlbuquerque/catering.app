<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Home</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>

<body class="event-page">
    <main class="page-frame page-frame--single">
        <section class="page-hero">
            <span class="page-kicker">Catering Platform</span>
            <h1>Comece criando sua conta de fornecedor de eventos.</h1>
            <p>Crie seu acesso com e-mail e senha e siga para o cadastro do seu perfil na plataforma.</p>
        </section>

        <section class="page-content">
            <div class="form-shell quick-actions-shell">
                <header class="form-header">
                    <div class="form-header-copy">
                        <span class="form-eyebrow">Acesso</span>
                        <h2>${message}</h2>
                        <p>Entre na sua conta ou crie um novo acesso como fornecedor de eventos.</p>
                    </div>
                </header>

                <div class="quick-actions">
                    <a class="btn-secondary" href="/accounts/login">Login</a>
                    <a class="btn-save" href="/accounts/sign-up/event-providers">Criar conta de fornecedor</a>
                </div>
            </div>
        </section>
    </main>
</body>
</html>
