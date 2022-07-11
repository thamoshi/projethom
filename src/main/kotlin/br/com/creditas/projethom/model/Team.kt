package br.com.creditas.projethom.model

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Team(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long,
  val name: String,
  val description: String,
  val tribe: Tribe,
  @OneToMany
  val members: List<Employee> = ArrayList(),
  @OneToMany
  val systems: List<Systems> = ArrayList(),
  val createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)