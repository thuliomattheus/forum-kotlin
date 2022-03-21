package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.exception.CourseNotFoundException
import br.com.alura.forum.exception.UserNotFoundException
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicViewMapper(
    private val userService: UserService,
    private val courseService: CourseService,
): Mapper<NewTopicForm, Topic> {

    override fun map(t: NewTopicForm) = Topic(
        id = t.id,
        title = t.title,
        message = t.message,
        author = userService.findById(t.authorId) ?: throw UserNotFoundException(),
        course = courseService.findById(t.courseId) ?: throw CourseNotFoundException(),
    )
}