package me.mryakar.skma.note.repository

import me.mryakar.skma.note.entity.NoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NoteRepository : JpaRepository<NoteEntity, Long> {}