package me.mryakar.skma.note.service

import me.mryakar.skma.note.dto.NoteDto

interface NoteService {
    fun create(text: String): NoteDto
    fun read(id: Long): NoteDto
    fun readAll(): List<NoteDto>
    fun update(id: Long, text: String): NoteDto
    fun delete(id: Long)
}