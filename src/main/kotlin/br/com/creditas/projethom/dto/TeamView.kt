package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Tribe
import java.time.LocalDateTime
import java.util.UUID

data class TeamView(
  val id: UUID,
  val name: String,
  val description: String?,
  val tribe: Tribe,
  val createdAt: LocalDateTime
  )
