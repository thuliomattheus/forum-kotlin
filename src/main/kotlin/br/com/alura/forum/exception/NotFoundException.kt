package br.com.alura.forum.exception

open class NotFoundException(override val message: String): RuntimeException(message)
