package br.com.creditas.projethom.dto

import br.com.creditas.projethom.exception.NotEnumValueException
import br.com.creditas.projethom.model.Team
import br.com.creditas.projethom.model.Tribe
import javax.validation.constraints.Size

data class TeamRequest(
    @field:Size(min = 1, max = 50, message = "Team name must have between 1 and 50 characters")
    val name: String,
    val description: String,
    val tribe: String
) {
    companion object {
        fun toEntity(teamRequest: TeamRequest): Team {
            try {
                val newTribe = Tribe.valueOf(teamRequest.tribe.uppercase())
                return Team(
                    name = teamRequest.name,
                    description = teamRequest.description,
                    tribe = newTribe
                )
            } catch (e: IllegalArgumentException) {
                throw NotEnumValueException("tribe not found. Must be in ${Tribe.values().toList()}")
            }
        }
    }

}
