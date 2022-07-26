package br.com.creditas.projethom.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Team(
    @Id
    val id: UUID = UUID.randomUUID(),
    var name: String,
    var description: String? = null,
    @Enumerated(EnumType.STRING)
    var tribe: Tribe,
    @OneToMany(mappedBy = "team")
    val members: List<Employee> = ArrayList(),
    @OneToMany(mappedBy = "owner")
    val systems: List<System> = ArrayList(),
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)
