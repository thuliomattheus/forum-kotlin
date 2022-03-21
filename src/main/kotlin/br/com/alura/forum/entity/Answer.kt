package br.com.alura.forum.entity

import java.time.LocalDateTime

data class Answer (
    val id: Long? = null,
    val message: String,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val author: User,
    val topic: Topic,
    val solution: Boolean,
)
