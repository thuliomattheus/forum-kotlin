package br.com.alura.forum.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
class Role(val name: String) :
GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    override fun getAuthority() = name
}