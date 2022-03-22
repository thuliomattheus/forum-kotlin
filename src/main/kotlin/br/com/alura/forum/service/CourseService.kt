package br.com.alura.forum.service

import br.com.alura.forum.dto.NewCourseForm
import br.com.alura.forum.entity.Course
import br.com.alura.forum.mapper.CourseViewMapper
import br.com.alura.forum.repository.CourseRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val courseViewMapper: CourseViewMapper,
) {

    fun findAll(pageable: Pageable): Page<Course> = courseRepository.findAll(pageable)

    fun findById(id: Long): Optional<Course> = courseRepository.findById(id)

    @Transactional
    fun save(newCourseDto: NewCourseForm): Course =
        courseViewMapper.map(newCourseDto)
            .let {
                courseRepository.save(it)
            }
}