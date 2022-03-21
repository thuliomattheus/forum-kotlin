package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.service.TopicService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/topic")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Topic>> = ResponseEntity.ok(topicService.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Topic?> = ResponseEntity.ok(topicService.findById(id))

    @PostMapping
    fun save(@RequestBody @Valid topicForm: NewTopicForm): ResponseEntity<Topic> {
        return try {
            ResponseEntity(topicService.save(topicForm), HttpStatus.CREATED)
        } catch (exception: RuntimeException) {
            exception.printStackTrace()
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}