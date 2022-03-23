package br.com.alura.forum.entity

import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    val user: User
): UserDetails {

    override fun getAuthorities() = user.roles
    override fun getPassword() = user.password
    override fun getUsername() = user.email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}
