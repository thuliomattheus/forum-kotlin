package br.com.alura.forum.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity(name = "forum_user")
class User(
    val name: String,
    val email: String,
    @JsonIgnore var password: String,
) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    var roles: List<Role> = mutableListOf()
}
