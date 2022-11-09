package me.mryakar.skma.note.mapper

import me.mryakar.skma.note.entity.NoteEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
class NoteMapperTest {

    @Autowired
    lateinit var mapper: NoteMapper

    @Test
    fun dto_toEntity_entity() {
        val entity = NoteEntity(1, "Note")

        val dto = mapper.toDto(entity)

        assertThat(entity.id).isEqualTo(dto.id)
        assertThat(entity.text).isEqualTo(dto.text)
    }
}