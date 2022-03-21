package br.com.alura.forum.dto

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class NewTopicForm(
    val id: Long,

    @field:NotBlank @field:Size(min = 5, max = 100) val title: String,
    @field:NotBlank val message: String,
    @field:NotNull val courseId: Long,
    @field:NotNull val authorId: Long,
)