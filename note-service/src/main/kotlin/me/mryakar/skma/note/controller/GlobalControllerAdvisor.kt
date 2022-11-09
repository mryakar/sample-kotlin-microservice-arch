package me.mryakar.skma.note.controller

import me.mryakar.skma.note.util.ExceptionResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import javax.persistence.EntityNotFoundException

@RestControllerAdvice
class GlobalControllerAdvisor {
    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(
        exception: EntityNotFoundException,
        request: WebRequest
    ): ResponseEntity<ExceptionResponse> {
        return ResponseEntity(
            ExceptionResponse(
                exception.message ?: "No exception message",
                request.getDescription(false)
            ),
            HttpStatus.NOT_FOUND
        )
    }
}