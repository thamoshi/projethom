package br.com.creditas.projethom.model

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class System(
  @Id
  val id: UUID = UUID.randomUUID(),
  val name: String,
  @ManyToOne
  val owner: Team? = null,
  val documentation: String,
  val url: String,
  val createdAt: LocalDateTime = LocalDateTime.now(),
  var updatedAt: LocalDateTime = LocalDateTime.now()
)
