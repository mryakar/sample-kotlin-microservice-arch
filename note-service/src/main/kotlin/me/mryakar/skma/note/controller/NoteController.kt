package me.mryakar.skma.note.controller

import me.mryakar.skma.note.dto.NoteDto
import me.mryakar.skma.note.service.NoteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/note")
class NoteController(val service: NoteService) {

    @PostMapping
    fun create(@RequestBody noteDto: NoteDto) = service.create(noteDto.text)

    @GetMapping
    fun readAll() = ResponseEntity(service.readAll(), HttpStatus.OK)

    @GetMapping("/{id}")
    fun read(@PathVariable id: Long) = service.read(id)

    @PutMapping
    fun update(@RequestBody noteDto: NoteDto) = service.update(noteDto.id, noteDto.text)
}