package com.where2beer.ws.common.security.config;

import com.where2beer.ws.common.security.filter.FirebaseFilter;
import com.where2beer.ws.common.security.provider.FirebaseAuthenticationProvider;
import com.where2beer.ws.user.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final FirebaseAuthenticationProvider firebaseAuthenticationProvider;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers(HttpMethod.GET, "/countries/search")
                .antMatchers(HttpMethod.GET, "/beers/search")
                .antMatchers(HttpMethod.GET, "/bars/search")
                .antMatchers(HttpMethod.GET, "/users/search/**")
                .antMatchers(HttpMethod.GET, "/beers/types/search");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authenticationProvider(firebaseAuthenticationProvider)
                .addFilterBefore(new FirebaseFilter(), BasicAuthenticationFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/countries", "/beers", "/beers/types").hasAuthority(UserRole.EDITOR.name())
                .antMatchers(HttpMethod.PUT, "/countries", "/beers", "/beers/types").hasAuthority(UserRole.EDITOR.name())
                .antMatchers("/users**").hasAuthority(UserRole.ADMIN.name())
                .antMatchers(HttpMethod.DELETE).hasAuthority(UserRole.ADMIN.name())
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        var config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));
        config.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name()
        ));
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        var bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }
}
