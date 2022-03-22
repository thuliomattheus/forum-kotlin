package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewUserForm
import br.com.alura.forum.entity.User
import br.com.alura.forum.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Optional
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<User>> = ResponseEntity.ok(userService.findAll(pageable))

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Optional<User>> = ResponseEntity.ok(userService.findById(id))

    @PostMapping
    fun save(@RequestBody @Valid newUserDto: NewUserForm): ResponseEntity<User> = ResponseEntity(
        userService.save(newUserDto),
        HttpStatus.CREATED
    )
}