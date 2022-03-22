package br.com.alura.forum.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Answer (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long,
    val message: String,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne val author: User,
    @ManyToOne val topic: Topic,
    val solution: Boolean = false,
)
