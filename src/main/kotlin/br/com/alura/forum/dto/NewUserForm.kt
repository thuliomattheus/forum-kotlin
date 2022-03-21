package br.com.alura.forum.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class NewUserForm(
    val id: Long,
    @field:NotNull @field:NotBlank val name: String,
    @field:NotNull @field:Email val email: String,
)
