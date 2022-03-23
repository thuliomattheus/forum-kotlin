package br.com.alura.forum.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService
) : WebSecurityConfigurerAdapter()
{

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity?) {
        http?.run {
            authorizeRequests()
                .antMatchers("/topics").hasRole("READ_ONLY")
                .anyRequest()
                .authenticated()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .httpBasic()
            .and()
            .formLogin()
                .disable()
//            .csrf()
//                .disable()
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.run {
            userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder())
        }
    }
}
