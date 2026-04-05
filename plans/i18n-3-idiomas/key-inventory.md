# Inventario Inicial de Textos e Chaves

## Objetivo

Este arquivo lista a primeira rodada de superficies com texto user-facing que devem ser migradas para internacionalizacao. O inventario esta organizado por dominio para orientar a implementacao.

## Bundles atuais

- Existe hoje `src/main/resources/messages.properties` com poucas chaves de `eventProviderDto.*` e `paymentMethod.*`.
- O bundle atual tambem apresenta sinais de encoding incorreto em alguns valores.
- A estrategia definida para a feature e substituir este arquivo por:
  - `messages_pt_BR.properties`
  - `messages_en.properties`
  - `messages_es.properties`

## Home

Arquivo principal:

- `src/main/webapp/WEB-INF/views/home.jsp`

Textos a migrar:

- `home.title`
- `home.hero.kicker`
- `home.hero.title`
- `home.hero.description`
- `home.card.eyebrow`
- `home.card.description`
- `home.actions.login`
- `home.actions.signUp`
- `home.brand.name`

Observacoes:

- O `${message}` hoje vem de `HomeController` e esta servindo como nome da plataforma. Vale substituir por chave direta na view ou por atributo com code resolvido.

## Login

Arquivos:

- `src/main/webapp/WEB-INF/views/account/loginForm.jsp`
- `src/main/java/com/catering/app/account/AccountController.java`
- `src/main/java/com/catering/app/account/AccountService.java`
- `src/main/java/com/catering/app/account/request/AccountLoginRequest.java`

Textos a migrar:

- `account.login.title`
- `account.login.hero.kicker`
- `account.login.hero.title`
- `account.login.hero.description`
- `account.login.header.eyebrow`
- `account.login.header.title`
- `account.login.fields.email.label`
- `account.login.fields.email.placeholder`
- `account.login.fields.password.label`
- `account.login.fields.password.placeholder`
- `account.login.actions.submit`
- `account.login.actions.support`
- `account.login.feedback.invalidForm`
- `account.login.feedback.invalidCredentials`

Validacoes:

- `validation.accountLoginRequest.email.notBlank`
- `validation.accountLoginRequest.email.email`
- `validation.accountLoginRequest.password.notBlank`
- `validation.accountLoginRequest.password.size`

## Cadastro de conta

Arquivos:

- `src/main/webapp/WEB-INF/views/account/eventProviderSignUpForm.jsp`
- `src/main/java/com/catering/app/account/AccountController.java`
- `src/main/java/com/catering/app/account/AccountService.java`
- `src/main/java/com/catering/app/account/request/EventProviderAccountCreateRequest.java`

Textos a migrar:

- `account.signup.title`
- `account.signup.hero.kicker`
- `account.signup.hero.title`
- `account.signup.hero.description`
- `account.signup.hero.point1.title`
- `account.signup.hero.point1.description`
- `account.signup.hero.point2.title`
- `account.signup.hero.point2.description`
- `account.signup.header.eyebrow`
- `account.signup.header.title`
- `account.signup.header.description`
- `account.signup.section.credentials.title`
- `account.signup.section.credentials.description`
- `account.signup.fields.email.label`
- `account.signup.fields.email.placeholder`
- `account.signup.fields.password.label`
- `account.signup.fields.password.placeholder`
- `account.signup.actions.support`
- `account.signup.actions.submit`
- `account.signup.feedback.invalidForm`
- `account.signup.feedback.success`
- `account.signup.feedback.duplicateEmail`

Validacoes:

- `validation.eventProviderAccountCreateRequest.email.notBlank`
- `validation.eventProviderAccountCreateRequest.email.email`
- `validation.eventProviderAccountCreateRequest.password.notBlank`
- `validation.eventProviderAccountCreateRequest.password.size`

## Dashboard

Arquivos:

- `src/main/webapp/WEB-INF/views/dashboard.jsp`
- `src/main/java/com/catering/app/dashboard/DashboardController.java`

Textos a migrar:

- `dashboard.title`
- `dashboard.topbar.signOut`
- `dashboard.hero.kicker`
- `dashboard.hero.title`
- `dashboard.header.eyebrow`
- `dashboard.header.title`

Observacoes:

- `Sign out` esta em ingles enquanto o restante da tela esta em portugues.
- `${message}` hoje vem do controller e deve virar chave localizada.

## Cadastro e edicao de fornecedor

Arquivos:

- `src/main/webapp/WEB-INF/views/eventProvider/eventProviderCreateForm.jsp`
- `src/main/webapp/WEB-INF/views/eventProvider/eventProviderEditForm.jsp`
- `src/main/webapp/WEB-INF/tags/eventProvider/eventProviderForm.tag`
- `src/main/webapp/WEB-INF/tags/shared/incrementalField.tag`
- `src/main/java/com/catering/app/eventprovider/EventProviderController.java`
- `src/main/java/com/catering/app/eventprovider/request/EventProviderBaseRequest.java`

Textos a migrar:

- `eventProvider.create.title`
- `eventProvider.create.hero.kicker`
- `eventProvider.create.hero.title`
- `eventProvider.create.hero.description`
- `eventProvider.create.hero.point1.title`
- `eventProvider.create.hero.point1.description`
- `eventProvider.create.hero.point2.title`
- `eventProvider.create.hero.point2.description`
- `eventProvider.create.hero.point3.title`
- `eventProvider.create.hero.point3.description`
- `eventProvider.create.form.title`
- `eventProvider.create.feedback.invalidForm`
- `eventProvider.create.feedback.success`

- `eventProvider.edit.title`
- `eventProvider.edit.hero.kicker`
- `eventProvider.edit.hero.title`
- `eventProvider.edit.hero.description`
- `eventProvider.edit.hero.point1.title`
- `eventProvider.edit.hero.point1.description`
- `eventProvider.edit.hero.point2.title`
- `eventProvider.edit.hero.point2.description`
- `eventProvider.edit.hero.point3.title`
- `eventProvider.edit.hero.point3.description`
- `eventProvider.edit.form.title`
- `eventProvider.edit.feedback.invalidForm`
- `eventProvider.edit.feedback.success`

- `eventProvider.form.header.eyebrow.create`
- `eventProvider.form.header.eyebrow.edit`
- `eventProvider.form.header.support.create`
- `eventProvider.form.header.support.edit`
- `eventProvider.form.section.profile.title`
- `eventProvider.form.section.profile.description`
- `eventProvider.form.section.contact.title`
- `eventProvider.form.section.contact.description`
- `eventProvider.form.section.address.title`
- `eventProvider.form.section.address.description`
- `eventProvider.form.section.payment.title`
- `eventProvider.form.section.payment.description`
- `eventProvider.form.section.gallery.title`
- `eventProvider.form.section.gallery.description`
- `eventProvider.form.description.help`
- `eventProvider.form.gallery.label`
- `eventProvider.form.gallery.tip`
- `eventProvider.form.gallery.imageAlt`
- `eventProvider.form.actions.support`
- `eventProvider.form.actions.submit`
- `eventProvider.form.actions.addAnother`

Placeholders:

- `eventProvider.form.fields.tradingName.placeholder`
- `eventProvider.form.fields.companyName.placeholder`
- `eventProvider.form.fields.registrationNumber.placeholder`
- `eventProvider.form.fields.description.placeholder`
- `eventProvider.form.fields.phone.placeholder`
- `eventProvider.form.fields.email.placeholder`
- `eventProvider.form.fields.zipCode.placeholder`
- `eventProvider.form.fields.state.placeholder`
- `eventProvider.form.fields.city.placeholder`
- `eventProvider.form.fields.neighborhood.placeholder`

Observacoes:

- Parte dos labels ja usa `eventProviderDto.*`, mas ainda faltam placeholders, helper texts, titulos de secao e botoes.
- `incrementalField.tag` usa botao com texto `+ Adicionar outro ...`; isso precisa virar chave com parametro.

## Chaves existentes que devem ser revisadas

Bundle atual:

- `eventProviderDto.tradingName`
- `eventProviderDto.companyName`
- `eventProviderDto.registrationNumber`
- `eventProviderDto.description`
- `eventProviderDto.phones`
- `eventProviderDto.phone`
- `eventProviderDto.emails`
- `eventProviderDto.email`
- `eventProviderDto.zipCode`
- `eventProviderDto.neighborhood`
- `eventProviderDto.state`
- `eventProviderDto.city`
- `eventProviderDto.paymentMethods`
- `eventProviderDto.paymentMethods.help`
- `paymentMethod.PIX`
- `paymentMethod.CREDIT_CARD`
- `paymentMethod.DEBIT_CARD`
- `paymentMethod.CASH`
- `paymentMethod.BANK_TRANSFER`

Revisoes previstas:

- Corrigir acentuacao em `pt-BR`.
- Reavaliar se o prefixo `eventProviderDto` continua adequado ou se deve migrar para `eventProvider.form.fields.*`.

## Mensagens de controllers e services

Arquivos ja identificados:

- `src/main/java/com/catering/app/account/AccountController.java`
- `src/main/java/com/catering/app/account/AccountService.java`
- `src/main/java/com/catering/app/eventprovider/EventProviderController.java`
- `src/main/java/com/catering/app/dashboard/DashboardController.java`
- `src/main/java/com/catering/app/home/HomeController.java`

Mensagens a migrar:

- erros de validacao geral por formulario
- sucesso de criacao de conta
- sucesso de criacao de fornecedor
- sucesso de atualizacao de fornecedor
- credenciais invalidas
- e-mail duplicado
- nome da plataforma usado pelo `HomeController`
- titulo do dashboard vindo do controller

## Validacao Jakarta

Arquivos:

- `src/main/java/com/catering/app/account/request/AccountLoginRequest.java`
- `src/main/java/com/catering/app/account/request/EventProviderAccountCreateRequest.java`
- `src/main/java/com/catering/app/eventprovider/request/EventProviderBaseRequest.java`

Cobrir pelo menos:

- `NotBlank`
- `NotEmpty`
- `Email`
- `Size`
- `Pattern`

Possivel convencao:

- `validation.<request>.<field>.<constraint>`

## Mensagens tecnicas fora do escopo inicial de UI

Arquivos com texto literal encontrados:

- `src/main/java/com/catering/app/common/config/storage/FileSystemStorageService.java`
- `src/main/java/com/catering/app/account/PasswordHasher.java`
- `src/main/java/com/catering/app/eventprovider/EventProviderService.java`

Direcao sugerida:

- Internacionalizar apenas o que puder chegar ao usuario final nesta feature.
- Manter mensagens estritamente tecnicas fora do bundle se nao forem exibidas em UI.
- Se alguma excecao tecnica for promovida para tela no futuro, migrar naquele fluxo.

## Testes planejados

- teste de paridade de chaves entre `messages_pt_BR.properties`, `messages_en.properties` e `messages_es.properties`
- testes de locale com `?lang=en`
- testes de locale com `?lang=es`
- testes de mensagens de validacao localizadas
- testes de renderizacao padrao em `pt-BR`
