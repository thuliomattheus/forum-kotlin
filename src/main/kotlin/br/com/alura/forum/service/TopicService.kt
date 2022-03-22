package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.UpdatedTopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicMapper
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class TopicService(
    private val topicMapper: TopicMapper,
    private val topicRepository: TopicRepository,
) {

    fun findAll(
        courseName: String?,
        pageable: Pageable,
    ): Page<Topic> {
        return if (courseName == null) topicRepository.findAll(pageable)
            else topicRepository.findByCourseNameContaining(courseName, pageable)
    }

    fun findById(id: Long): Optional<Topic> = topicRepository.findById(id)

    @Transactional
    fun save(form: NewTopicForm): Topic =
        topicMapper
            .map(form)
            .apply {
                topicRepository.save(this)
            }

    @Transactional
    fun update(form: UpdatedTopicForm): Topic {
        val oldTopic = findById(form.id).orElseThrow { NotFoundException("Topic not found") }

        return topicMapper.map(form, oldTopic)
            .apply {
                topicRepository.save(this)
            }
    }

    @Transactional
    fun deleteById(id: Long) = topicRepository.deleteById(id)
}