package br.com.alura.forum.controller

import br.com.alura.forum.dto.NewCourseForm
import br.com.alura.forum.entity.Course
import br.com.alura.forum.service.CourseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/course")
class CourseController(private val courseService: CourseService) {

    @GetMapping
    fun findAll(pageable: Pageable): ResponseEntity<Page<Course>> = ResponseEntity.ok(courseService.findAll(pageable))

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Optional<Course>> = ResponseEntity.ok(courseService.findById(id))

    @PostMapping
    fun save(@RequestBody @Valid newCourseDto: NewCourseForm): ResponseEntity<Course> = ResponseEntity(
        courseService.save(newCourseDto),
        HttpStatus.CREATED
    )
}