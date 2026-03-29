<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body class="event-page">
    <main class="page-frame page-frame--single">
        <section class="page-topbar">
            <form method="post" action="/accounts/sign-out">
                <button class="btn-ghost" type="submit">Sign out</button>
            </form>
        </section>

        <section class="page-hero">
            <span class="page-kicker">Dashboard</span>
            <h1>${message}</h1>
        </section>

        <section class="page-content">
            <div class="form-shell quick-actions-shell">
                <header class="form-header">
                    <div class="form-header-copy">
                        <span class="form-eyebrow">Sessão ativa</span>
                        <h2>Dashboard</h2>
                    </div>
                </header>
            </div>
        </section>
    </main>
</body>
</html>
