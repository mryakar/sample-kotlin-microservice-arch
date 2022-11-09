package me.mryakar.skma.note.mapper

import me.mryakar.skma.note.dto.NoteDto
import me.mryakar.skma.note.entity.NoteEntity
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings

@Mapper(componentModel = "spring")
interface NoteMapper {

    @Mappings(
        Mapping(source = "entity.id", target = "id"),
        Mapping(source = "entity.text", target = "text")
    )
    fun toDto(entity: NoteEntity): NoteDto

    @Mappings(
        Mapping(source = "entity.id", target = "id"),
        Mapping(source = "entity.text", target = "text")
    )
    fun toDto(entityList: List<NoteEntity>): List<NoteDto>

    @Mappings(
        Mapping(source = "dto.id", target = "id"),
        Mapping(source = "dto.text", target = "text")
    )
    fun toEntity(dto: NoteDto): NoteEntity

    @Mappings(
        Mapping(source = "dto.id", target = "id"),
        Mapping(source = "dto.text", target = "text")
    )
    fun toEntity(dtoList: List<NoteDto>): List<NoteEntity>
}