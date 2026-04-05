# Internacionalizacao em 3 Idiomas

## Resumo

Implementar internacionalizacao completa para a aplicacao com tres idiomas padrao:

- `pt-BR`
- `en`
- `es`

O idioma padrao da aplicacao sera `pt-BR`, com troca via parametro `?lang=` e seletor simples na interface. A implementacao deve remover textos hardcoded de views, tags, controllers e mensagens de validacao, centralizando o conteudo em bundles Spring.

## Decisoes fechadas

- Usar bundles explicitos por idioma:
  - `messages_pt_BR.properties`
  - `messages_en.properties`
  - `messages_es.properties`
- Remover `messages.properties` para evitar ambiguidade e fallback silencioso.
- Configurar `pt-BR` como locale padrao.
- Usar `LocaleChangeInterceptor` com parametro `lang`.
- Garantir leitura UTF-8 nos bundles.
- Corrigir textos atuais em `pt-BR` com acentuacao correta.
- Incluir seletor simples de idioma na interface nesta mesma feature.

## Objetivos da implementacao

- Padronizar toda a copia user-facing do sistema em internacionalizacao.
- Tornar os tres idiomas equivalentes na estrutura do projeto.
- Eliminar problemas de encoding atualmente visiveis em texto como `RazÃ£o`, `SessÃ£o` e similares.
- Evitar que novas telas ou mensagens voltem a usar texto inline em Java ou JSP.

## Mudancas previstas

### 1. Infraestrutura de locale e mensagens

- Atualizar `WebMvcConfig` para registrar `LocaleChangeInterceptor`.
- Adicionar um `LocaleResolver` com default `pt-BR`.
- Configurar `MessageSource` com basename `messages` e encoding UTF-8.
- Garantir que a ausencia de chave falhe de forma visivel durante desenvolvimento e testes.

### 2. Bundles

- Migrar o conteudo atual de `src/main/resources/messages.properties` para `src/main/resources/messages_pt_BR.properties`.
- Criar `src/main/resources/messages_en.properties`.
- Criar `src/main/resources/messages_es.properties`.
- Remover `src/main/resources/messages.properties`.
- Padronizar nomenclatura de chaves por dominio funcional.

### 3. Extracao de textos

- Substituir textos hardcoded nas JSPs e tags por `<spring:message>`.
- Substituir mensagens literais em controllers por message codes.
- Parar de depender de `ex.getMessage()` como texto de apresentacao quando a mensagem for exibida ao usuario.
- Adicionar mensagens de validacao explicitas nas anotacoes Jakarta Validation.

### 4. Interface de troca de idioma

- Adicionar seletor simples com `pt-BR`, `en` e `es`.
- Preservar a pagina atual ao trocar idioma.
- Manter o visual coerente com o design system atual.

### 5. Qualidade e seguranca da mudanca

- Adicionar teste de paridade de chaves entre os tres bundles.
- Atualizar testes de integracao para conferir mensagens e labels em locale diferente.
- Validar renderizacao padrao em `pt-BR`.

## Estrategia de chaves

Organizar as chaves por area funcional, por exemplo:

- `common.*`
- `home.*`
- `account.login.*`
- `account.signup.*`
- `dashboard.*`
- `eventProvider.create.*`
- `eventProvider.edit.*`
- `eventProvider.form.*`
- `validation.*`
- `error.*`

## Riscos conhecidos

- Hoje existem textos em JSP, tags, controllers, services e possivelmente excecoes com encoding inconsistente.
- A remocao de `messages.properties` exige cobertura de testes para evitar chave faltante em runtime.
- Alguns fluxos ainda usam `message` no model como string pronta; esses pontos precisam migrar para codigos de mensagem ou mensagens resolvidas por locale.

## Criterios de aceite

- Todas as telas atuais renderizam sem texto hardcoded user-facing fora dos bundles, exceto conteudo estritamente tecnico nao exibido ao usuario.
- `pt-BR`, `en` e `es` possuem o mesmo conjunto de chaves.
- O app abre em `pt-BR` por padrao.
- `?lang=en` e `?lang=es` trocam o idioma da interface.
- O texto em `pt-BR` fica com acentuacao correta.
- Os testes automatizados cobrindo locale e paridade de chaves passam.
