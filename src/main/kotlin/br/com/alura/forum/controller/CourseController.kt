package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewCourseForm
import br.com.alura.forum.entity.Course
import br.com.alura.forum.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/course")
class CourseController(private val courseService: CourseService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Course>> = ResponseEntity.ok(courseService.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Course?> = ResponseEntity.ok(courseService.findById(id))

    @PostMapping
    fun save(@RequestBody @Valid newCourseDto: NewCourseForm): ResponseEntity<Boolean> = ResponseEntity(
        courseService.save(newCourseDto),
        HttpStatus.CREATED
    )
}