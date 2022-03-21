package br.com.alura.forum.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class CourseNotFoundException(message: String? = null) : RuntimeException(message ?: "That course was not found")
