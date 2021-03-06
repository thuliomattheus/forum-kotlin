package br.com.alura.forum.repository

import br.com.alura.forum.entity.User
import br.com.alura.forum.entity.UserDetail
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserRepository: JpaRepository<User, Long> {

    fun findByEmail(email: String): Optional<User>
}
