
# 🔐 Spring Security - Microservices Security Overview

This document summarizes key concepts, components, and best practices for implementing security in Spring Boot microservices using **Spring Security**.

---

## 🚀 What Is Spring Security?
Spring Security is a powerful, customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.

---

## 🧠 Concepts to Remember

- **Authentication** = Who are you?
- **Authorization** = Are you allowed to access this?
- **Principal** = The currently authenticated user.
- **Roles/Authorities** = Permissions assigned to a principal.
- **Stateless** JWT-based systems should NOT use session.

---

## 📦 Dependencies & Starters

The main dependency for enabling Spring Security is:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

### ⚙️ Transitive Frameworks Included:
- `spring-security-core`
- `spring-security-web`
- `spring-security-config`
- `spring-security-oauth2-resource-server` (optional)
- `spring-security-oauth2-jose` (for JWT)
- `spring-security-oauth2-client` (for OAuth login)
- `jakarta.servlet` and filter-related APIs

---

## 🔄 Request Flow in Spring Security

```
HTTP Request
   ↓
[Servlet Filters]
   → DelegatingFilterProxy
      ↓
   → FilterChainProxy (Spring Security Filteer Chain)
      ↓
   → Filters (in order):
       - CorsFilter
       - CsrfFilter
       - LogoutFilter
       - UsernamePasswordAuthenticationFilter
       - JwtAuthenticationFilter (custom)
       - BasicAuthenticationFilter
       - ExceptionTranslationFilter
       - FilterSecurityInterceptor
   ↓
[Controller Execution]
```

---

## 🔁 Key Filters and Components

| Filter / Component                | Purpose                                      |
|----------------------------------|----------------------------------------------|
| `SecurityFilterChain`            | Defines the configuration for filters        |
| `UsernamePasswordAuthenticationFilter` | Handles login requests                   |
| `BasicAuthenticationFilter`      | Basic Auth credentials from headers          |
| `OncePerRequestFilter`           | Custom filter for token/JWT validation       |
| `ExceptionTranslationFilter`     | Catches exceptions and translates responses  |
| `FilterSecurityInterceptor`      | Authorizes based on roles                   |

---

## 🧰 Other Useful Components

| Component                     | Description                              |
|------------------------------|------------------------------------------|
| `HandlerInterceptor`         | Pre/Post handling (non-security logic)   |
| `WebMvcConfigurer`           | Register interceptors, CORS, etc.        |
| `GlobalFilter` (Gateway)     | Global API gateway filtering             |
| `GatewayFilter`              | Route-specific filter in gateway         |
| `SecurityContextHolder`     | Stores authenticated user information    |

---

## 🔑 Token-Based Security (JWT)

- Use `OncePerRequestFilter` to intercept JWT in headers.
- Validate and parse JWT → Extract user/roles → Set in `SecurityContext`.

---

## ⚠️ Filter Order Tips

- Filters are ordered. Use `addFilterBefore()` or `addFilterAfter()` to place yours correctly.
```java
http.addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);
```

---

## 🌐 OAuth2 and Authorization Server (Advanced)

- For centralized login or third-party integrations, use:
  - `spring-security-oauth2-client`
  - `spring-authorization-server` (newer alternative to legacy `uaa`)

---

## 🛡️ Best Practices

- Always validate JWT signature and expiration.
- Keep security stateless in microservices.
- Use HTTPS in production.
- Hide implementation details with proper exception handling.

---

## 📚 References

- [Spring Security Docs](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Spring Blog on Security](https://spring.io/blog/category/security)
- [Spring Authorization Server](https://github.com/spring-projects/spring-authorization-server)

---

_This guide is meant to summarize and reinforce your understanding of Spring Security in microservice applications._

