package br.com.alura.forum.service

import br.com.alura.forum.dto.NewTopicForm
import br.com.alura.forum.dto.UpdatedTopicForm
import br.com.alura.forum.entity.Topic
import br.com.alura.forum.mapper.TopicMapper
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val topicMapper: TopicMapper,
    private var topics: MutableList<Topic> = ArrayList(),
) {

    fun findAll() = topics

    fun findById(id: Long): Topic? = topics.find {
        it.id == id
    }

    fun save(form: NewTopicForm): Topic =
        topicMapper
            .map(form)
            .apply {
                topics.add(this)
            }

    fun update(form: UpdatedTopicForm): Topic? {
        val oldTopic = findById(form.id) ?: return null

        return topicMapper.map(form, oldTopic)
            .apply {
                topics[(form.id - 1).toInt()] = this
            }
    }

    fun deleteById(id: Long) {
        val topic = topics.find { it.id == id } ?: return

        topics = topics.minus(topic).toMutableList()
    }
}