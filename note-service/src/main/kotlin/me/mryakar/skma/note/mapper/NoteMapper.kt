package me.mryakar.skma.note.mapper

import me.mryakar.skma.note.dto.NoteDto
import me.mryakar.skma.note.entity.NoteEntity
import org.springframework.stereotype.Component

@Component
class NoteMapper {

    fun toDto(entity: NoteEntity) = NoteDto(entity.id, entity.text)

    fun toDto(entityList: List<NoteEntity>) = entityList.map { toDto(it) }

    fun toEntity(dto: NoteDto) = NoteEntity(dto.id, dto.text)

    fun toEntity(dtoList: List<NoteDto>) = dtoList.map { toEntity(it) }

}