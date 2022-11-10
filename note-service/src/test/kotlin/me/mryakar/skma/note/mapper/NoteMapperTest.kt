package me.mryakar.skma.note.mapper

import me.mryakar.skma.note.dto.NoteDto
import me.mryakar.skma.note.entity.NoteEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class NoteMapperTest {

    private val mapper = NoteMapper()

    @Test
    fun `entity toDto dto`() {
        val entity = NoteEntity(1, "Note")

        val dto = mapper.toDto(entity)

        assertThat(dto.id).isEqualTo(entity.id)
        assertThat(dto.text).isEqualTo(entity.text)
    }

    @Test
    fun `entity list toDto dto list`() {
        val entity = NoteEntity(1, "Note")

        val dtoList = mapper.toDto(listOf(entity))

        assertThat(dtoList).hasSize(1)
        assertThat(dtoList[0].id).isEqualTo(entity.id)
        assertThat(dtoList[0].text).isEqualTo(entity.text)
    }

    @Test
    fun `dto toEntity entity`() {
        val dto = NoteDto(1, "Note")

        val entity = mapper.toEntity(dto)

        assertThat(entity.id).isEqualTo(dto.id)
        assertThat(entity.text).isEqualTo(dto.text)
    }

    @Test
    fun `dto list toEntity entity list`() {
        val dto = NoteDto(1, "Note")

        val entityList = mapper.toEntity(listOf(dto))

        assertThat(entityList).hasSize(1)
        assertThat(entityList[0].id).isEqualTo(dto.id)
        assertThat(entityList[0].text).isEqualTo(dto.text)
    }
}