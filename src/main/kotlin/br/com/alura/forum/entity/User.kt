package br.com.alura.forum.entity

data class User (
    val id: Long? = null,
    val name: String,
    val email: String,
) {
    companion object Users {

    }
}