package br.com.alura.forum.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(
        exception: NotFoundException,
        request: HttpServletRequest
    ): Object {
        return object: Object() {
            val timestamp: LocalDateTime = LocalDateTime.now()
            val status: Int = HttpStatus.NOT_FOUND.value()
            val error: String = HttpStatus.NOT_FOUND.name
            val message: String = exception.message
            val path: String = request.servletPath
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): Object {
        val errorMessage = HashMap<String, String?>()
        exception.bindingResult.fieldErrors.forEach {
            errorMessage[it.field] = it.defaultMessage
        }
        return object: Object() {
            val timestamp: LocalDateTime = LocalDateTime.now()
            val status: Int = HttpStatus.INTERNAL_SERVER_ERROR.value()
            val error: String = HttpStatus.INTERNAL_SERVER_ERROR.name
            val path: String = request.servletPath
            val message: String = errorMessage.toString()
                .replace("{", "")
                .replace("}", "")
                .replace("=", " = ")
        }
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleServerError(
        exception: Exception,
        request: HttpServletRequest
    ): Object {
        return object: Object() {
            val timestamp: LocalDateTime = LocalDateTime.now()
            val status: Int = HttpStatus.INTERNAL_SERVER_ERROR.value()
            val error: String = HttpStatus.INTERNAL_SERVER_ERROR.name
            val message: String = exception.message ?: "Server Internal Error"
            val path: String = request.servletPath
        }
    }
}