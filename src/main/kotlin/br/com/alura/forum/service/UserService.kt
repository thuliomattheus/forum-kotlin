package br.com.alura.forum.service

import br.com.alura.forum.dto.NewUserForm
import br.com.alura.forum.entity.User
import br.com.alura.forum.mapper.UserViewMapper
import org.springframework.stereotype.Service

@Service
class UserService(
    private var users: MutableList<User>,
    private val userViewMapper: UserViewMapper,
) {

    fun findAll(): List<User> = users

    fun findById(id: Long): User? = users.find { it.id == id }

    fun save(newUserDto: NewUserForm): Boolean =
        userViewMapper.map(newUserDto)
            .let {
                users.add(it)
            }
}