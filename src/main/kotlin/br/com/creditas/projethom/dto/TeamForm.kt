package br.com.creditas.projethom.dto

import br.com.creditas.projethom.model.Tribe
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class TeamForm (
  @field:NotEmpty
  @field:Size(min=1,max=50, message = "Team name must have between 1 and 50 characters")
  val name: String,
  val description: String,
  val tribe: Tribe
  )
