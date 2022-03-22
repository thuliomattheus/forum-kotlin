package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.UpdatedTopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.exception.CourseNotFoundException
import br.com.alura.forum.exception.UserNotFoundException
import br.com.alura.forum.service.CourseService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicMapper(
    private val userService: UserService,
    private val courseService: CourseService,
) {

    fun map(newTopic: NewTopicForm) = Topic (
        title = newTopic.title,
        message = newTopic.message,
        author = userService.findById(newTopic.authorId).orElseThrow { UserNotFoundException() },
        course = courseService.findById(newTopic.courseId).orElseThrow { CourseNotFoundException() },
    )

    fun map(updatedTopic: UpdatedTopicForm, oldTopic: Topic) = Topic (
        id = updatedTopic.id,
        title = updatedTopic.title,
        message = updatedTopic.message,
        author = oldTopic.author,
        course = oldTopic.course,
        status = oldTopic.status,
        createdDate = oldTopic.createdDate,
        answers = oldTopic.answers
    )
}