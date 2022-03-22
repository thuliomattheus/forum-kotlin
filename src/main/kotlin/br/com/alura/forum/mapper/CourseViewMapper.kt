package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewCourseForm
import br.com.alura.forum.entity.Course
import org.springframework.stereotype.Component

@Component
class CourseViewMapper {

    fun map(t: NewCourseForm) = Course (
        name = t.name,
        category = t.category
    )
}