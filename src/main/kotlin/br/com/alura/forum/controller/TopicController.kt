package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.UpdatedTopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.service.TopicService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.Optional
import javax.validation.Valid

@RestController
@RequestMapping("/topic")
class TopicController(private val topicService: TopicService) {

    @GetMapping
    fun findAll(
        @RequestParam(required = false) courseName: String?,
        pageable: Pageable
    ): ResponseEntity<Page<Topic>> = ResponseEntity.ok(topicService.findAll(courseName, pageable))

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Optional<Topic>> = ResponseEntity.ok(topicService.findById(id))

    @PostMapping
    fun save(
        @RequestBody @Valid form: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Topic> {
        return topicService.save(form).let { topic ->
            val uriLocation = uriBuilder.path("/topics/${topic.id}").build().toUri()
            ResponseEntity.created(uriLocation).body(topic)
        }
    }

    @PutMapping
    fun update(@RequestBody @Valid form: UpdatedTopicForm): ResponseEntity<Topic> {
        return ResponseEntity.ok(topicService.update(form))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) {
        topicService.deleteById(id)
    }
}