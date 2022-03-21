package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.exception.CourseNotFoundException
import br.com.alura.forum.exception.UserNotFoundException
import br.com.alura.forum.mapper.TopicViewMapper
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicViewMapper: TopicViewMapper,
    private var topics: MutableList<Topic> = ArrayList(),
) {

    /*
    init {
        topics =
            mutableListOf(
                Topic(
                    id = 1,
                    title = "Kotlin Doubt",
                    message = "How to get a resource with Spring and Kotlin",
                    course = Course(
                        name = "Kotlin + Spring",
                        category = "Programming Language",
                    ),
                    author = User(
                        name = "Thúlio",
                        email = "email_test@test.com",
                    ),
                )
            )
    }
     */

    fun findAll() = topics

    fun findById(id: Long): Topic? = topics.find {
        it.id == id
    }

    fun save(newTopicDto: NewTopicForm): Topic =
        topicViewMapper
            .map(newTopicDto)
            .apply {
                topics.add(this)
            }
}