package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NewCourseForm
import br.com.alura.forum.entity.Course
import org.springframework.stereotype.Component

@Component
class CourseViewMapper: Mapper<NewCourseForm, Course> {

    override fun map(t: NewCourseForm) = Course(
        id = t.id,
        name = t.name,
        category = t.category
    )
}