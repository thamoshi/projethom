package br.com.creditas.projethom.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Team(
  @Id
  val id: UUID = UUID.randomUUID(),
  val name: String,
  val description: String,
  val tribe: Tribe,
  @OneToMany
  val members: List<Employee> = ArrayList(),
  @OneToMany
  val systems: List<System> = ArrayList(),
  val createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)