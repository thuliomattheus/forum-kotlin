package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewUserForm
import br.com.alura.forum.entity.User
import org.springframework.stereotype.Component

@Component
class UserViewMapper: Mapper<NewUserForm, User> {

    override fun map(t: NewUserForm) = User(
        id = t.id,
        name = t.name,
        email = t.email
    )
}
