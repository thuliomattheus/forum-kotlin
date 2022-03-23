package br.com.alura.forum.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class NewUserForm(
    @field:NotBlank val name: String,
    @field:NotNull @field:Email val email: String,
    @field:NotBlank val password: String,
)
