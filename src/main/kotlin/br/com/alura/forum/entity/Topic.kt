package br.com.alura.forum.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topic (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    val title: String,
    val message: String,
    val createdDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne val course: Course,
    @ManyToOne val author: User,
    @Enumerated(value = EnumType.STRING) val status: TopicStatus = TopicStatus.UNRESOLVED,
    @OneToMany(mappedBy = "topic") val answers: List<Answer> = ArrayList(),
)