package br.com.alura.forum.entity

import java.time.LocalDateTime

data class Topic (
    val id: Long,
    val title: String,
    val message: String,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    val course: Course,
    val author: User,
    val status: TopicStatus = TopicStatus.UNRESOLVED,
    val answers: List<Answer> = ArrayList(),
)