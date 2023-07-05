package com.mindhub.homeBanking.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Configuration
@EnableWebSecurity
class WebAuthorization{
    @Autowired
    private WebAuthentication webAuthentication;
    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    protected SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/web/index.html","/api/login","/web/pages/login.html", "/web/style/**", "/web/script/**", "/web/images/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .antMatchers( "/admin/**","/api/clients", "/api/accounts" ,"/rest/**","/h2-console/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/clients/current/cards", "/api/clients/current/transactions").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/api/clients/current/accounts").hasAuthority("CLIENT")
                .antMatchers("/web/pages/**", "/api/clients/current").hasAuthority("CLIENT")
                .anyRequest().denyAll();
        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        // turn off checking for CSRF tokens
        http.csrf().disable();

        http.authenticationProvider(webAuthentication.authenticationProvider(userDetailsService));

        //disabling frameOptions so h2-console can be accessed
        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an 401 response
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response
        //http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        http.formLogin()
                .failureUrl("/api/login?error")
                .failureHandler((request, response, exception) -> {
                    if (exception instanceof UsernameNotFoundException) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        response.getWriter().write(exception.getMessage());
                        response.getWriter().flush();
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
                    }
                });

        // if logout is successful, just send a success response
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }

    }
}


