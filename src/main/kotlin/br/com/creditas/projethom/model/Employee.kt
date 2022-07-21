package br.com.creditas.projethom.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Employee(
    @Id
    val id: UUID = UUID.randomUUID(),
    val personId: UUID? = null,
    @ManyToOne
    val team: Team,
    val role: Role,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
