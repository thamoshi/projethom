package br.com.creditas.projethom.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class System(
    @Id
    val id: UUID = UUID.randomUUID(),
    var name: String,
    @ManyToOne
    var owner: Team? = null,
    var documentation: String,
    var url: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
