package me.mryakar.skma.note.service

import me.mryakar.skma.note.dto.NoteDto
import me.mryakar.skma.note.entity.NoteEntity
import me.mryakar.skma.note.mapper.NoteMapper
import me.mryakar.skma.note.repository.NoteRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class NoteServiceImpl(
    val mapper: NoteMapper,
    val repository: NoteRepository
) : NoteService {

    override fun create(text: String): NoteDto {
        var entity = NoteEntity()
        entity.text = text
        entity = repository.save(entity)
        return mapper.toDto(entity)
    }

    override fun read(id: Long): NoteDto {
        return mapper.toDto(repository.findById(id).orElseThrow { EntityNotFoundException() })
    }

    override fun readAll(): List<NoteDto> {
        return mapper.toDto(repository.findAll())
    }

    override fun update(id: Long, text: String): NoteDto {
        var entity = repository.findById(id).orElseThrow { EntityNotFoundException() }
        entity.text = text
        entity = repository.save(entity)
        return mapper.toDto(entity)
    }

    override fun delete(id: Long) {
        repository.findById(id).orElseThrow { EntityNotFoundException() }
        repository.deleteById(id)
    }
}