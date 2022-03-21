package br.com.alura.forum.service

import br.com.alura.forum.dto.NewCourseForm
import br.com.alura.forum.entity.Course
import br.com.alura.forum.mapper.CourseViewMapper
import org.springframework.stereotype.Service

@Service
class CourseService(
    private var courses: MutableList<Course> = ArrayList(),
    private val courseViewMapper: CourseViewMapper,
) {

    fun findAll(): List<Course> = courses

    fun findById(id: Long): Course? = courses.find { it.id == id }

    fun save(newCourseDto: NewCourseForm): Boolean =
        courseViewMapper
            .map(newCourseDto)
            .let {
                courses.add(it)
            }
}