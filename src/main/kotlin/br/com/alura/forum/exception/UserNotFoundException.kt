package br.com.alura.forum.exception

class UserNotFoundException(message: String = "That user was not found") : NotFoundException(message)
