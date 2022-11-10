package me.mryakar.skma.note.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.mryakar.skma.note.dto.NoteDto
import me.mryakar.skma.note.entity.NoteEntity
import me.mryakar.skma.note.mapper.NoteMapper
import me.mryakar.skma.note.repository.NoteRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*
import javax.persistence.EntityNotFoundException

@ExtendWith(MockKExtension::class)
class NoteServiceImplTest {

    @InjectMockKs
    lateinit var service: NoteServiceImpl

    @MockK
    lateinit var repository: NoteRepository

    @MockK
    lateinit var mapper: NoteMapper

    @Test
    fun `text create NoteDto`() {
        val entity = NoteEntity(1, "test")
        val dto = NoteDto(1, "test")

        every { repository.save(any()) } returns entity
        every { mapper.toDto(entity) } returns dto

        val createdDto = service.create("test")

        assertThat(createdDto.id).isEqualTo(dto.id)
        assertThat(createdDto.text).isEqualTo(dto.text)
        verify {
            repository.save(any())
            mapper.toDto(entity)
        }
    }

    @Test
    fun `readAll list of NoteDto`() {
        val id: Long = 1
        val text = "test"
        val entity = NoteEntity(id, text)
        val dto = NoteDto(id, text)
        val entityList = listOf(entity)
        val dtoList = listOf(dto)

        every { repository.findAll() } returns entityList
        every { mapper.toDto(entityList) } returns dtoList

        val noteList = service.readAll()

        assertThat(noteList).hasSize(1)
        assertThat(noteList[0].id).isEqualTo(id)
        assertThat(noteList[0].text).isEqualTo(text)
        verify {
            repository.findAll()
            mapper.toDto(entityList)
        }
    }

    @Test
    fun `note id read NoteDto`() {
        val id: Long = 1
        val text = "test"
        val entity = NoteEntity(id, text)
        val dto = NoteDto(id, text)

        every { repository.findById(id) } returns Optional.of(entity)
        every { mapper.toDto(entity) } returns dto

        val readEntity = service.read(id)

        assertThat(readEntity.id).isEqualTo(id)
        assertThat(readEntity.text).isEqualTo(text)
        verify {
            repository.findById(id)
            mapper.toDto(entity)
        }
    }

    @Test
    fun `note id read throw EntityNotFoundException`() {
        every { repository.findById(any()) } returns Optional.empty()

        assertThrows<EntityNotFoundException> {
            service.read(1L)
        }

        verify {
            repository.findById(any())
        }
    }

    @Test
    fun `id and new text update updated NoteDto`() {
        val id: Long = 1
        val oldText = "old"
        val oldEntity = NoteEntity(id, oldText)
        val newText = "new"
        val newEntity = NoteEntity(id, newText)
        val newDto = NoteDto(id, newText)

        every { repository.findById(id) } returns Optional.of(oldEntity)
        every { repository.save(oldEntity) } returns newEntity
        every { mapper.toDto(newEntity) } returns newDto

        val updatedDto = service.update(id, newText)

        assertThat(updatedDto.id).isEqualTo(id)
        assertThat(updatedDto.text).isEqualTo(newText)
        verify {
            repository.findById(id)
            repository.save(oldEntity)
            mapper.toDto(newEntity)
        }
    }

    @Test
    fun `id and new text update throws EntityNotFoundException`() {
        every { repository.findById(any()) } returns Optional.empty()

        assertThrows<EntityNotFoundException> {
            service.update(1L, "test")
        }

        verify {
            repository.findById(any())
        }
    }

    @Test
    fun `id delete`() {
        val id: Long = 1

        every { repository.findById(id) } returns Optional.of(NoteEntity(id, "test"))
        every { repository.deleteById(id) } returns Unit

        service.delete(id)

        verify {
            repository.findById(id)
            repository.deleteById(id)
        }
    }

    @Test
    fun `id delete throw EntityNotFoundException`() {
        every { repository.findById(any()) } returns Optional.empty()

        assertThrows<EntityNotFoundException> {
            service.delete(1)
        }

        verify {
            repository.findById(any())
        }
    }

}