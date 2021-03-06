package br.com.alura.forum.service

import br.com.alura.forum.dto.NewUserForm
import br.com.alura.forum.entity.User
import br.com.alura.forum.entity.UserDetail
import br.com.alura.forum.exception.UserNotFoundException
import br.com.alura.forum.mapper.UserViewMapper
import br.com.alura.forum.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val userViewMapper: UserViewMapper,
//    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
) : UserDetailsService
{

    fun findAll(pageable: Pageable): Page<User> = userRepository.findAll(pageable)

    fun findById(id: Long): Optional<User> = userRepository.findById(id)

    @Transactional
    fun save(newUserDto: NewUserForm): User =
        userViewMapper.map(newUserDto)
/*
            .apply {
                password = bCryptPasswordEncoder.encode(password)
            }
 */
            .let {
                userRepository.save(it)
            }

    override fun loadUserByUsername(username: String): UserDetail =
        userRepository
            .findByEmail(username)
            .orElseThrow {
                UserNotFoundException()
            }
        .run {
            UserDetail(this)
        }
}
